/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.ImplementedBy;

/** @author Mark Christiaens - Initial contribution */ 

@ImplementedBy (AlwaysMissModelCache.class)
public interface IModelCache {
	final public static String OMIT_NODE_MODEL = "org.eclipse.xtext.modelcache.IModelCache.OMIT_NODE_MODEL";
	final public static String DO_NOT_CONSULT_CACHE = "org.eclipse.xtext.modelcache.IModelCache.DO_NOT_CONSULT_CACHE";

	Resource load (ResourceSet resourceSet, URI uri, boolean addNodeModel) throws IOException;
	
	void add (ResourceSet resourceSet, URI uri) throws IOException; 
	
	void clear () throws IOException;
	
	public void init(File cacheLocation) throws IOException; 
}
