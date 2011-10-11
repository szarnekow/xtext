/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.util.Iterator;

import com.google.common.collect.ImmutableList;

/** @author Mark Christiaens - Initial contribution */ 

public class LRUReplacementStrategy implements IReplacementStrategy {
	private long maxSize;  

	public LRUReplacementStrategy (long maxSize) {
		this.maxSize = maxSize; 
	}
	
	public LRUReplacementStrategy() {
		 this (100 * Util.MIB);
	}
	
	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public ImmutableList<ICacheEntry> selectReplacementCandidates(IModelCacheIndex index, ICacheEntry entry) {
		ImmutableList.Builder<ICacheEntry> candidates = new ImmutableList.Builder<ICacheEntry>(); 
		
		long currentSize = index.getTotalOrigContentSize();
		long newSize = currentSize + entry.getOrigContentSize(); 
		
		Iterator<ICacheEntry> entriesByAge = index.getEntriesByAge();
		
		while (newSize > maxSize && entriesByAge.hasNext()) {
			ICacheEntry candidate = entriesByAge.next();
			newSize -= candidate.getOrigContentSize(); 
			candidates.add (candidate); 
		}
		
		return candidates.build(); 
	}
	
	public boolean canFit(ICacheEntry entry) {
		return entry.getOrigContentSize() <= maxSize;  
	}
}
