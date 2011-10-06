/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.modelcache;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.nodemodel.serialization.ISerializationService;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author mark.christiaens - Initial contribution and API
 */
@Singleton
public class TempModelCache extends DefaultModelCache{
	private boolean isLastAccessHit;
	
	@Inject
	public TempModelCache(ISerializationService serializationService, IReplacementStrategy replacementStrategy) throws IOException {
		super(serializationService, replacementStrategy);
		init(calcModelCacheLocation());
		isLastAccessHit = false; 
	}
	
	public static File calcModelCacheLocation() throws IOException {
		return File.createTempFile("cachedir", ""); 
	}
	
	@Override
	protected XtextResource handleMiss(XtextResourceSet resourceSet, URI uri, DigestInfo digestInfo) throws IOException {
		isLastAccessHit = false; 
		return super.handleMiss(resourceSet, uri, digestInfo);
	}
	
	@Override
	protected XtextResource handleHit(XtextResourceSet resourceSet, URI uri, ICacheEntry cacheEntry,
			boolean requireNodeModel) throws IOException {
		isLastAccessHit = true; 
		return super.handleHit(resourceSet, uri, cacheEntry, requireNodeModel);
	}

	public boolean isLastAccessHit() {
		return isLastAccessHit;
	}
}
