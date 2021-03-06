/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.impl.AbstractResourceDescriptionChangeEventSource;
import org.eclipse.xtext.resource.impl.ChangedResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionChangeEvent;
import org.eclipse.xtext.ui.editor.IDirtyStateManager;
import org.eclipse.xtext.ui.notification.IStateChangeEventBroker;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class DirtyStateAwareResourceDescriptions extends AbstractResourceDescriptionChangeEventSource implements IResourceDescriptions {

	private final IDirtyStateManager dirtyStateManager;
	private final IBuilderState globalDescriptions;
	
	@Inject
	public DirtyStateAwareResourceDescriptions(
			IBuilderState globalDescriptions, 
			IDirtyStateManager dirtyStateManager, 
			IStateChangeEventBroker stateChangeEventBroker) {
		this.globalDescriptions = globalDescriptions;
		this.dirtyStateManager = dirtyStateManager;
		globalDescriptions.addListener(createGlobalStateListener());
		dirtyStateManager.addListener(createDirtyStateListener());
		addListener(stateChangeEventBroker);
	}
	
	protected IResourceDescription.Event.Listener createDirtyStateListener() {
		return new DirtyStateListener();
	}
	
	protected IResourceDescription.Event.Listener createGlobalStateListener() {
		return new GlobalStateListener();
	}
	
	public void dirtyDescriptionsChanged(IResourceDescription.Event event) {
		ResourceDescriptionChangeEvent changeEvent = new ResourceDescriptionChangeEvent(
				Iterables.transform(event.getDeltas(), new Function<IResourceDescription.Delta, IResourceDescription.Delta>() {
					public IResourceDescription.Delta apply(IResourceDescription.Delta from) {
						IResourceDescription.Delta result = from;
						if (from.getNew() == null) {
							result = createDelta(from.getOld(), globalDescriptions.getResourceDescription(from.getUri()));
						} else if (from.getOld() == null) {
							result = createDelta(globalDescriptions.getResourceDescription(from.getUri()), from.getNew());
						}
						return result;
					}
				}), this);
		notifyListeners(changeEvent);
	}
	
	protected IResourceDescription.Delta createDelta(IResourceDescription old, IResourceDescription _new) {
		return new ChangedResourceDescriptionDelta(old, _new);
	}
	
	public void globalDescriptionsChanged(IResourceDescription.Event event) {
		ResourceDescriptionChangeEvent changeEvent = new ResourceDescriptionChangeEvent(
				Iterables.filter(event.getDeltas(), new Predicate<IResourceDescription.Delta>() {
					public boolean apply(Delta input) {
						URI uri = input.getUri();
						return !dirtyStateManager.hasContent(uri);
					}
				}), this);
		notifyListeners(changeEvent);
	}

	protected class DirtyStateListener implements IResourceDescription.Event.Listener {

		public void descriptionsChanged(IResourceDescription.Event event) {
			dirtyDescriptionsChanged(event);
		}
		
	}
	
	protected class GlobalStateListener implements IResourceDescription.Event.Listener {

		public void descriptionsChanged(IResourceDescription.Event event) {
			globalDescriptionsChanged(event);
		}
		
	}

	public Iterable<IResourceDescription> getAllResourceDescriptions() {
		return Iterables.transform(globalDescriptions.getAllResourceDescriptions(), new Function<IResourceDescription, IResourceDescription>() {
			public IResourceDescription apply(IResourceDescription from) {
				IResourceDescription dirty = dirtyStateManager.getDirtyResourceDescription(from.getURI());
				if (dirty != null)
					return dirty;
				return from;
			}
		});
	}

	public IResourceDescription getResourceDescription(URI uri) {
		IResourceDescription result = dirtyStateManager.getDirtyResourceDescription(uri);
		if (result == null)
			result = globalDescriptions.getResourceDescription(uri);
		return result;
	}

	public boolean isEmpty() {
		return globalDescriptions.isEmpty();
	}

	public Iterable<IEObjectDescription> getExportedObjects() {
		return Iterables.concat(Iterables.transform(getAllResourceDescriptions(), new Function<ISelectable, Iterable<IEObjectDescription>>() {
			public Iterable<IEObjectDescription> apply(ISelectable from) {
				if (from != null)
					return from.getExportedObjects();
				return Collections.emptyList();
			}
		}));
	}

	public Iterable<IEObjectDescription> getExportedObjects(EClass type, QualifiedName name, boolean ignoreCase) {
		Iterable<IEObjectDescription> dirtyDescriptions = dirtyStateManager.getExportedObjects(type, name, ignoreCase);
		Iterable<IEObjectDescription> persistentDescriptions = globalDescriptions.getExportedObjects(type, name, ignoreCase);
		return joinIterables(dirtyDescriptions, persistentDescriptions);
	}

	protected Iterable<IEObjectDescription> joinIterables(Iterable<IEObjectDescription> dirtyDescriptions,
			Iterable<IEObjectDescription> persistentDescriptions) {
		Iterable<IEObjectDescription> filteredPersistent = Iterables.filter(persistentDescriptions, new Predicate<IEObjectDescription>() {
			public boolean apply(IEObjectDescription input) {
				URI resourceURI = input.getEObjectURI().trimFragment();
				if (dirtyStateManager.hasContent(resourceURI))
					return false;
				return true;
			}
		});
		return Iterables.concat(dirtyDescriptions, filteredPersistent);
	}

	public Iterable<IEObjectDescription> getExportedObjectsByType(EClass type) {
		Iterable<IEObjectDescription> dirtyDescriptions = dirtyStateManager.getExportedObjectsByType(type);
		Iterable<IEObjectDescription> persistentDescriptions = globalDescriptions.getExportedObjectsByType(type);
		return joinIterables(dirtyDescriptions, persistentDescriptions);
	}

	public Iterable<IEObjectDescription> getExportedObjectsByObject(EObject object) {
		URI resourceURI = EcoreUtil2.getNormalizedResourceURI(object);
		if (dirtyStateManager.hasContent(resourceURI))
			return dirtyStateManager.getExportedObjectsByObject(object);
		return globalDescriptions.getExportedObjectsByObject(object);
	}
}
