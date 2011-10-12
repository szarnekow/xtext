/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.xtext.util.StringInputStream;

import com.google.common.collect.Maps;

public class SyntheticModelAwareURIConverter extends ExtensibleURIConverterImpl {
	private final Map<URI, String> models = Maps.newHashMap();

	public void addModel(String uri, String content) {
		models.put(URI.createURI(uri), content);
	}

	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		boolean result = models.containsKey(uri);
		if (!result) {
			for (URI knownUri : models.keySet()) {
				if (uri.toString().endsWith(knownUri.toString()))
					return true;
			}
		}
		return result;
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (models.containsKey(uri)) {
			return new StringInputStream(models.get(uri));
		}

		return null;
	}
}