/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.modelcache;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IResourceFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class CachedResourceFactory implements IResourceFactory {
	private Provider<CachedResource> provider;

	@Inject
	public CachedResourceFactory(Provider<CachedResource> resourceProvider) {
		this.provider = resourceProvider;
	}

	public Resource createResource(URI uri) {
		CachedResource resource = provider.get();
		resource.setURI(uri);
		return resource;
	}
}
