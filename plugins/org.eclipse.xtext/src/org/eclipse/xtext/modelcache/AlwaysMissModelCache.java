/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/** @author Mark Christiaens */

public class AlwaysMissModelCache implements IModelCache {
	public Resource load(ResourceSet resourceSet, URI uri, boolean addNodeModel) {
		return null;
	}

	public void clear() {
	}

	public void init(File cacheLocation) throws IOException {
	}

	public void add(ResourceSet resourceSet, URI uri) throws IOException {
	}
}
