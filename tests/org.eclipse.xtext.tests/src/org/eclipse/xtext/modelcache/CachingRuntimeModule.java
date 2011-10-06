/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.testlanguages.ReferenceGrammarTestLanguageRuntimeModule;

/** @author Mark Christiaens - Initial contribution */ 
public class CachingRuntimeModule extends ReferenceGrammarTestLanguageRuntimeModule {
	@Override
	public Class<? extends IResourceFactory> bindIResourceFactory() {
		return CachedResourceFactory.class; 
	}
	
	public Class<? extends IModelCache> bindIModelCache() {
		return TempModelCache.class; 
	}
}