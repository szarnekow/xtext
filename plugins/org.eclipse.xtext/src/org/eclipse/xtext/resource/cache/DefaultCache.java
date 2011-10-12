/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import static org.eclipse.xtext.resource.cache.CacheUtil.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.nodemodel.serialization.ISerializationService;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

/**
 * @author Mark Christiaens - Initial contribution
 * 
 * @since 2.1
 */

public class DefaultCache implements ICache {
	private static final Logger LOGGER = Logger.getLogger(DefaultCache.class);

	private ICacheIndex index;
	private final ISerializationService serializationService;
	private final IReplacementStrategy replacementStrategy;

	/** Directory into which the entire cache will live */
	private File cacheLocation;

	@Inject
	public DefaultCache(ISerializationService serializationService, IReplacementStrategy replacementStrategy) {
		this.serializationService = serializationService;
		this.replacementStrategy = replacementStrategy;
	}

	public Resource load(ResourceSet resourceSet, URI uri, boolean requireNodeModel) throws IOException {
		checkProperlyInitialized();

		if (resourceSet.getResource(uri, false) == null) {
			throw new IllegalArgumentException("There should be a resource with URI " + uri
					+ " already available in the resource set as a receptacle for the cached content");
		}

		if (resourceSet instanceof XtextResourceSet) {
			try {
				XtextResourceSet xtextResourceSet = (XtextResourceSet) resourceSet;
				return loadResourceFromCache(xtextResourceSet, uri, requireNodeModel);
			} catch (IOException e) {
				resourceSet.getResource(uri, false).unload();
				LOGGER.error("Could not load " + uri + " from cache: clearing cache", e);
				try {
					clear();
				} catch (IOException ee) {
					LOGGER.error("Could not clear cache", e);
					throw ee;
				}
			}
		}

		return resourceSet.getResource(uri, true);
	}

	protected void checkProperlyInitialized() {
		if (cacheLocation == null) {
			throw new IllegalStateException("The cache's location has not yet been configured.");
		}
	}

	public void add(ResourceSet resourceSet, URI uri) throws IOException {
		checkProperlyInitialized();

		if (!(resourceSet instanceof XtextResourceSet)) {
			return;
		}

		XtextResourceSet xrs = (XtextResourceSet) resourceSet;
		XtextResource xr = (XtextResource) xrs.getResource(uri, false);

		if (xr == null) {
			LOGGER.error("Ignoring request to add a a resource to the cache but the resource does not exist: " + uri);
			return;
		}

		if (!xr.isLoaded()) {
			LOGGER.error("Ignoring request to add a a resource to the cache but the resource is not yet loaded: " + uri);
			return;
		}

		DigestInfo digestInfo = org.eclipse.xtext.resource.cache.CacheUtil.calcDigestInfo(xrs, uri);

		if (index.get(digestInfo.getDigest()) != null) {
			/* Someone already added an entry (other thread) so we don't do anything anymore */
			return;
		}

		ICacheEntry cacheEntry = index.createNewEntry(digestInfo, getContentDirectory());
		try {
			if (replacementStrategy.canFit(cacheEntry)) {
				ImmutableList<ICacheEntry> toRemove = replacementStrategy
						.selectReplacementCandidates(index, cacheEntry);
				removeEntries(toRemove);
				writeEntryContent(xr, cacheEntry);
				index.add(cacheEntry);
				CacheUtil.write(index, getIndexFile(), LOGGER);
			}
		} catch (IOException e) {
			cleanupEntry(cacheEntry);
			LOGGER.error("Could not add an entry to the cache: " + e);
			throw e;
		}
	}

	public void clear() throws IOException {
		checkProperlyInitialized();

		LOGGER.info("Clearing model cache");

		org.eclipse.xtext.resource.cache.CacheUtil.deleteFileOrDirectory(cacheLocation);
		org.eclipse.xtext.resource.cache.CacheUtil.mkdir(cacheLocation);
		org.eclipse.xtext.resource.cache.CacheUtil.mkdir(getContentDirectory());
		index = new DefaultCacheIndex();
		CacheUtil.write(index, getIndexFile(), LOGGER);
	}

	public void init(File cacheLocation) throws IOException {
		this.cacheLocation = cacheLocation;

		try {
			readData();
		} catch (IOException e) {
			clear();
		}
	}

	protected void readData() throws IOException {
		DataInputStream dis = null;
		try {
			dis = getIndexStream();
			index = DefaultCacheIndex.read(dis);
		} finally {
			CacheUtil.tryClose(dis, DefaultCache.LOGGER);
		}

		if (!getContentDirectory().exists()) {
			throw new IOException("There is no content directory for the model cache");
		}
	}

	protected DataInputStream getIndexStream() throws FileNotFoundException, IOException {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(getIndexFile());
			BufferedInputStream bis = new BufferedInputStream(fis);
			GZIPInputStream gis = new GZIPInputStream(bis);
			BufferedInputStream bgis = new BufferedInputStream(gis);
			DataInputStream dis = new DataInputStream(bgis);

			return dis;
		} catch (IOException e) {
			CacheUtil.tryClose(fis, LOGGER);
			throw e;
		}
	}

	protected File getContentDirectory() {
		return new File(cacheLocation, "content");
	}

	protected File getIndexFile() {
		return new File(cacheLocation, "index.ser");
	}

	protected XtextResource loadResourceFromCache(XtextResourceSet resourceSet, URI uri, boolean requireNodeModel)
			throws IOException {
		if (resourceSet.getResource(uri, false) == null) {
			throw new IllegalArgumentException("Cannot load content of resource since resource is not created yet: "
					+ uri);
		}

		DigestInfo digestInfo;
		try {
			digestInfo = calcDigestInfo(resourceSet, uri);
		} catch (IOException e) {
			return null;
		}

		LOGGER.debug("Loading " + uri + " with digest " + digestInfo.getDigest());

		ICacheEntry cacheEntry = index.get(digestInfo.getDigest());

		if (cacheEntry != null) {
			return handleHit(resourceSet, uri, cacheEntry, requireNodeModel);
		}

		return null;
	}

	private void cleanupEntry(ICacheEntry cacheEntry) throws IOException {
		CacheUtil.deleteFileOrDirectory(getEntryDir(cacheEntry));
		index.remove(cacheEntry.getDigest());
	}

	private void removeEntries(ImmutableList<ICacheEntry> toRemove) throws IOException {
		for (ICacheEntry entry : toRemove) {
			LOGGER.info("Removing entry for digest " + entry.getDigest());
			index.remove(entry.getDigest());
			CacheUtil.deleteFileOrDirectory(getEntryDir(entry));
		}
	}

	protected void writeEntryContent(XtextResource resource, ICacheEntry cacheEntry) throws IOException,
			FileNotFoundException {
		File entryDir = getEntryDir(cacheEntry);
		File emfFile = getEMFFile(cacheEntry);
		File nodeModelFile = getNodeModelFile(cacheEntry);

		if (!entryDir.exists()) {
			throw new IllegalArgumentException("The directory for storing the cache entry does not exist: "
					+ entryDir.getAbsolutePath());
		}

		if (!entryDir.isDirectory()) {
			throw new IllegalArgumentException("The location for storing the cache entry is not a directory: "
					+ entryDir.getAbsolutePath());
		}

		OutputStream emfOut = null;
		OutputStream nodeOut = null;

		try {
			emfOut = getOutputStream(emfFile);
			nodeOut = getOutputStream(nodeModelFile);

			serializationService.write(resource, emfOut, nodeOut);
		} finally {
			CacheUtil.tryClose(emfOut, LOGGER);
			CacheUtil.tryClose(nodeOut, LOGGER);
		}
	}

	protected BufferedOutputStream getOutputStream(File emfFile) throws IOException {
		final FileOutputStream out = new FileOutputStream(emfFile);

		try {
			return new BufferedOutputStream(new GZIPOutputStream(new BufferedOutputStream(out)));
		} catch (IOException e) {
			out.close();
			throw e;
		}
	}

	protected File getNodeModelFile(ICacheEntry cacheEntry) {
		return combinePaths(getContentDirectory(), cacheEntry.getRelativeNodeModelFilePath());
	}

	protected File getEMFFile(ICacheEntry cacheEntry) {
		return combinePaths(getContentDirectory(), cacheEntry.getRelativeEMFFilePath());
	}

	protected File getEntryDir(ICacheEntry cacheEntry) {
		return combinePaths(getContentDirectory(), cacheEntry.getRelativeCacheEntryDirPath());
	}

	protected XtextResource handleHit(XtextResourceSet resourceSet, URI uri, ICacheEntry cacheEntry,
			boolean requireNodeModel) throws IOException {
		return getResource(resourceSet, uri, cacheEntry, requireNodeModel);
	}

	protected XtextResource getResource(XtextResourceSet resourceSet, URI uri, ICacheEntry cacheEntry,
			boolean requireNodeModel) throws IOException {
		File emfFile = getEMFFile(cacheEntry);
		File nodeFile = getNodeModelFile(cacheEntry);

		InputStream emfIn = null;
		InputStream nodeIn = null;

		try {
			emfIn = getInputStream(emfFile);
			nodeIn = requireNodeModel ? getInputStream(nodeFile) : null;

			XtextResource resource = serializationService.getResource(resourceSet, uri, emfIn, nodeIn);

			return resource;
		} finally {
			CacheUtil.tryClose(emfIn, LOGGER);
			CacheUtil.tryClose(nodeIn, LOGGER);
		}
	}

	protected BufferedInputStream getInputStream(File emfFile) throws IOException {
		return new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(emfFile))));
	}

	static public File combinePaths(File absolutePath, File relativePath) {
		return new File(absolutePath, relativePath.getPath());
	}
}
