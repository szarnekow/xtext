/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.builderState;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.google.common.collect.ImmutableList;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Sebastian Zarnekow
 */
public interface IBuilderState extends IResourceDescriptions, IResourceDescription.Event.Source {
	
	/**
	 * Updated the underlying {@link IResourceDescriptions} according to the passed URIs.
	 * It updates transitively affected {@link IResourceDescription} as well.
	 * 
	 * The change to the underlying {@link IResourceDescriptions} is guaranteed to be atomic.
	 * 
	 * @param toBeAddedOrUpdated a map containing URI to String, where the String is the external representation of the underlying storage.
	 * @param toBeRemoved the uris to be removed.
	 * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
	 *        to call done() on the given monitor. Accepts null, indicating that no progress should be
	 *        reported and that the operation cannot be canceled.
	 * 
	 * @return a list of changes in the form of deltas.
	 */
	ImmutableList<IResourceDescription.Delta> update(BuildData buildData, IProgressMonitor monitor);
	
	/**
	 * Remove the descriptions for the given resource-uris from the {@link IResourceDescriptions}.
	 * The clean-operation is not transitive.
	 * 
	 * The change to the underlying {@link IResourceDescriptions} is guaranteed to be atomic.
	 * 
	 * @param toBeRemoved the uris or the cleaned resources.
	 * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
	 *        to call done() on the given monitor. Accepts null, indicating that no progress should be
	 *        reported and that the operation cannot be canceled.
	 *        
	 * @return a list of changes in the form of deltas.
	 */
	ImmutableList<IResourceDescription.Delta> clean(Set<URI> toBeRemoved, IProgressMonitor monitor);
}
