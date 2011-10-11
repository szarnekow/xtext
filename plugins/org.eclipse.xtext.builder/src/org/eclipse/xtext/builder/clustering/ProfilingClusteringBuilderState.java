/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.builder.clustering;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.builderState.ResourceDescriptionsData;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.nodemodel.serialization.SerializationUtil;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.google.common.collect.ImmutableList;

/** @author Mark Christiaens - Initial contribution */ 

public class ProfilingClusteringBuilderState extends ClusteringBuilderState {
    private static final Logger LOGGER = Logger.getLogger(ProfilingClusteringBuilderState.class);
    
    @Override
    protected Collection<Delta> doUpdate(BuildData buildData, ResourceDescriptionsData newData, IProgressMonitor monitor) {
    	long start = System.nanoTime();
    	Collection<Delta> result = super.doUpdate(buildData, newData, monitor); 
    	long stop = System.nanoTime(); 
    	LOGGER.info("Doing build (creating global index, linking, validation) took: " + SerializationUtil.milliDiff(start, stop) + " ms");
    	
    	return result; 
    }
    
    @Override
	protected void writeNewResourceDescriptions(
            BuildData buildData,
            IResourceDescriptions oldState,
            CurrentDescriptions newState,
            final IProgressMonitor monitor) {
    	long start = System.nanoTime(); 
    	super.writeNewResourceDescriptions(buildData, oldState, newState, monitor); 
    	long stop = System.nanoTime();
    	
    	LOGGER.info("Creating new resource descriptions (global scope) took: " + SerializationUtil.milliDiff(start, stop) + " ms");
    }

    @Override
	protected void queueAffectedResources(
    		Set<URI> allRemainingURIs, 
    		IResourceDescriptions oldState, 
    		CurrentDescriptions newState,
            Collection<Delta> deltas, 
            BuildData buildData, 
            final IProgressMonitor monitor) {
    	long start = System.nanoTime(); 
    	super.queueAffectedResources(allRemainingURIs, oldState, newState, deltas, buildData, monitor);
    	long stop = System.nanoTime(); 
    	
    	LOGGER.info("Queueing affected resources took: " + SerializationUtil.milliDiff(start, stop) + " ms"); 
    }
    
    @Override
    protected void updateMarkers(ResourceSet resourceSet, ImmutableList<Delta> deltas, IProgressMonitor monitor) {
    	long start = System.nanoTime(); 
    	super.updateMarkers(resourceSet, deltas, monitor);
    	long stop = System.nanoTime(); 
    	LOGGER.info("Updating markers took: " + SerializationUtil.milliDiff(start, stop) + " ms");
    }
    
    @Override
    public synchronized ImmutableList<Delta> clean(Set<URI> toBeRemoved, IProgressMonitor monitor) {
    	long start = System.nanoTime(); 
    	final ImmutableList<Delta> result = super.clean(toBeRemoved, monitor);
    	long stop = System.nanoTime(); 
    	LOGGER.info("Cleaning took: "  + SerializationUtil.milliDiff(start, stop) + " ms");

		return result;
    }
}
