/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource.cache;

import java.io.File;
import java.io.IOException;

import org.eclipse.xtext.nodemodel.serialization.ISerializationService;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author mark.christiaens - Initial contribution and API
 */
@Singleton
public class TempModelCache extends DefaultCache {
	private boolean isLastAccessHit;

	@Inject
	public TempModelCache(ISerializationService serializationService, IReplacementStrategy replacementStrategy)
			throws IOException {
		super(serializationService, replacementStrategy);
		init(calcModelCacheLocation());
		isLastAccessHit = false;
	}
	
	public static File calcModelCacheLocation() throws IOException {
		return File.createTempFile("cachedir", "");
	}
	
	@Override
	public XtextResource load(XtextResource xr, byte[] content, String encoding, boolean addNodeModel) throws IOException {
		isLastAccessHit = false; 
		return super.load(xr, content, encoding, addNodeModel);
	}

	@Override
	protected XtextResource handleHit(XtextResource xr, ICacheEntry cacheEntry, boolean requireNodeModel) throws IOException {
		isLastAccessHit = true;
		return super.handleHit(xr, cacheEntry, requireNodeModel);
	}

	public boolean isLastAccessHit() {
		return isLastAccessHit;
	}
}
