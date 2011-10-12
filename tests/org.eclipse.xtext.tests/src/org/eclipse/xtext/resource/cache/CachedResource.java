/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource.cache;

import static org.eclipse.xtext.resource.cache.ICache.*;
import static org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.cache.ICache;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class CachedResource extends LazyLinkingResource {
	private static final ImmutableSet<String> GOOD_KEYS = ImmutableSet.of(NAMED_BUILDER_SCOPE, OPTION_ENCODING,
			OMIT_NODE_MODEL);
	@Inject
	ICache modelCache;

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		setEncodingFromOptions(options);

		/* Avoid dealing with dirty resources that could contain resolved proxies */
		if (getContents().isEmpty()) {
			boolean loadNodeModel = (options == null || options.get(OMIT_NODE_MODEL) == null || options
					.get(OMIT_NODE_MODEL) == Boolean.FALSE);

			if (goodOptions(options) && resourceSet != null && uri != null) {
				Resource cachedResource = doCacheLoad(loadNodeModel);
				if (cachedResource != null) {
					return;
				}
			}
		}

		doNormalLoad(inputStream);
	}

	protected Resource doCacheLoad(boolean loadNodeModel) throws IOException {
		Resource resource = null;
		try {
			resource = modelCache.load(resourceSet, uri, loadNodeModel);
			clearErrorsAndWarnings();

			if (resource != null && loadNodeModel) {
				reattachModificationTracker(getParseResult().getRootASTElement());
				addSyntaxErrors();
			}
		} catch (WrappedException e) {
		}

		return resource;
	}

	protected void doNormalLoad(InputStream inputStream) throws UnsupportedEncodingException {
		IParseResult result = getParser().parse(new InputStreamReader(inputStream, getEncoding()));
		updateInternalState(result);

		try {
			modelCache.add(resourceSet, uri);
		}
		/* Something went wrong while trying to add stuff to the cache -> ignore */
		catch (IOException e) {
		}
	}

	private static boolean goodOptions(Map<?, ?> options) {
		if (options == null) {
			return true;
		}

		Set<?> keySet = options.keySet();

		return Sets.difference(keySet, GOOD_KEYS).isEmpty();
	}
}
