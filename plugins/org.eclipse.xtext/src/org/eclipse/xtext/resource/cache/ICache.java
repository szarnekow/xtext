/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.ImplementedBy;

/**
 * A cache for EMF and INode models. Creating the EMF and INode models for a resource requires lexing, parsing, building
 * the models ... and can be quite time consuming. An ICache can cache the resulting EMF and INode models so that, when
 * a resource is loaded again, the EMF and INode models can be reconstructed considerably faster.
 * 
 * @author Mark Christiaens - Initial contribution
 * @since 2.1
 */

@ImplementedBy(AlwaysMissCache.class)
public interface ICache {
	/**
	 * Load option for a resource set so that only the EMF model is loaded for a resource. The backing INode model
	 * underneath the EMF model is not installed. Results in faster load times but reduced functionality. Use with
	 * caution.
	 */
	final public static String OMIT_NODE_MODEL = "org.eclipse.xtext.resource.cache.ICache.OMIT_NODE_MODEL";

	/**
	 * Load option for a resource set so that the cache is skipped and resources are constructed from the content of the
	 * resource and not from the cache's content.
	 */
	final public static String DO_NOT_CONSULT_CACHE = "org.eclipse.xtext.resource.cache.ICache.DO_NOT_CONSULT_CACHE";

	/**
	 * Load the content of a resource from cache
	 * 
	 * @param resourceSet
	 *            The resource set holding a (empty) resource
	 * @param uri
	 *            The URI of the resource in the resource set that needs to be loaded. The corresponding resource must
	 *            already be present in the resource set so that it can be filled.
	 * @param addNodeModel
	 *            Also load the INode model and properly interconnect it with the EMF resource.
	 * 
	 * @return The resource that was loaded.
	 * 
	 * @throws IOException
	 *             when reading data from the cache caused an IO exception.
	 * */
	Resource load(ResourceSet resourceSet, URI uri, boolean addNodeModel) throws IOException;

	/**
	 * Present a resource to the cache for optional inclusion. This may result in the removal of other resources from
	 * the cache.
	 * 
	 * @param resourceSet
	 *            Resource set holding the resource
	 * @param uri
	 *            URI of the resource to cache. The resource must be present in the resource set.
	 * 
	 * @throws IOException
	 *             when storing the content of the resource failed.
	 */

	void add(ResourceSet resourceSet, URI uri) throws IOException;

	/**
	 * Empty the content of the cache.
	 */
	void clear() throws IOException;

	/**
	 * Point the cache to a certain directory for holding its backing storage.
	 * 
	 * @param cacheLocation
	 *            The directory where the content of the cache will be stored.
	 * 
	 * @throws IOException
	 *             when IO goes wrong when accessing the cache.
	 */
	public void init(File cacheLocation) throws IOException;
}
