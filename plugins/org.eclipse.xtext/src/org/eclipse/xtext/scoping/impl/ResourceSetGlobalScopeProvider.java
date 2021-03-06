/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.scoping.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.resource.ResourceSetReferencingResourceSet;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * A {@link IGlobalScopeProvider} which puts all elements in the {@link ResourceSet} on the scope.
 * 
 * @author Sven Efftinge - Initial contribution and API
 */
public class ResourceSetGlobalScopeProvider extends AbstractGlobalScopeProvider {
	
	@Inject
	private GlobalResourceDescriptionProvider resourceDecriptionProvider;
	
	public void setGlobalResourceDecriptionProvider(GlobalResourceDescriptionProvider resourceDecriptionProvider) {
		this.resourceDecriptionProvider = resourceDecriptionProvider;
	}

	@Override
	protected IScope getScope(Resource resource, boolean ignoreCase, EClass type,
			Predicate<IEObjectDescription> filter) {
		IScope parent = IScope.NULLSCOPE;
		if (resource == null || resource.getResourceSet() == null)
			return parent;
		final ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet instanceof ResourceSetReferencingResourceSet) {
			ResourceSetReferencingResourceSet set = (ResourceSetReferencingResourceSet) resourceSet;
			Iterable<ResourceSet> referencedSets = Iterables.reverse(set.getReferencedResourceSets());
			for (ResourceSet referencedSet : referencedSets) {
				parent = createScopeWithQualifiedNames(parent, resource, filter, referencedSet, type, ignoreCase);
			}
		}
		return createScopeWithQualifiedNames(parent, resource, filter, resourceSet, type, ignoreCase);
	}
	
	protected IScope createScopeWithQualifiedNames(final IScope parent, final Resource resource,
			final Predicate<IEObjectDescription> filter, ResourceSet resourceSet, EClass type, boolean ignoreCase) {
		final Iterable<ISelectable> resourceDescriptions = Iterables.transform(resourceSet.getResources(), new Function<Resource, ISelectable>() {
			public ISelectable apply(Resource from) {
				return resourceDecriptionProvider.getResourceDescription(from);
			}
		});
		ISelectable compound = new ISelectable() {
			
			public boolean isEmpty() {
				for (ISelectable description: resourceDescriptions) {
					if (!description.isEmpty())
						return false;
				}
				return true;
			}
			
			public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
				return Iterables.concat(Iterables.transform(resourceDescriptions, new Function<ISelectable, Iterable<IEObjectDescription>>() {
					public Iterable<IEObjectDescription> apply(ISelectable from) {
						return from.getExportedObjectsByType(type);
					}
				}));
			}
			
			public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
				return Iterables.concat(Iterables.transform(resourceDescriptions, new Function<ISelectable, Iterable<IEObjectDescription>>() {
					public Iterable<IEObjectDescription> apply(ISelectable from) {
						return from.getExportedObjectsByObject(object);
					}
				}));
			}
			
			public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
				return Iterables.concat(Iterables.transform(resourceDescriptions, new Function<ISelectable, Iterable<IEObjectDescription>>() {
					public Iterable<IEObjectDescription> apply(ISelectable from) {
						return from.getExportedObjects(type, name, ignoreCase);
					}
				}));
			}
			
			public Iterable<IEObjectDescription> getExportedObjects() {
				return Iterables.concat(Iterables.transform(resourceDescriptions, new Function<ISelectable, Iterable<IEObjectDescription>>() {
					public Iterable<IEObjectDescription> apply(ISelectable from) {
						return from.getExportedObjects();
					}
				}));
			}
		};
		return SelectableBasedScope.createScope(parent, compound, filter, type, ignoreCase);
	}

}
