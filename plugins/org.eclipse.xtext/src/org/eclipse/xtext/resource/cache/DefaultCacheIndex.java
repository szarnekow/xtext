/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

/** @author Mark Christiaens - Initial contribution */

public class DefaultCacheIndex implements ICacheIndex {
	private static final int VERSION = 1;

	private static final Logger LOGGER = Logger.getLogger(DefaultCacheIndex.class);

	public Map<BigInteger, ICacheEntry> entriesMap;
	public LinkedHashSet<ICacheEntry> lruSet;
	public long totalOrigContentSize;

	public DefaultCacheIndex() {
		allocateBasicStructures(0);
	}

	private void allocateBasicStructures(int expectedSize) {
		totalOrigContentSize = 0;
		entriesMap = Maps.newHashMapWithExpectedSize(expectedSize);
		lruSet = new LinkedHashSet<ICacheEntry>(expectedSize);
	}

	public void add(ICacheEntry entry) {
		checkInvariants();
		BigInteger digest = entry.getDigest();

		if (entriesMap.containsKey(digest)) {
			throw new IllegalArgumentException("Cache entry with digest: " + digest + " is already present");
		}

		totalOrigContentSize += entry.getOrigContentSize();
		entriesMap.put(digest, entry);
		lruSet.add(entry);
		checkInvariants();
	}

	public void remove(BigInteger digest) {
		checkInvariants();

		ICacheEntry entry = entriesMap.get(digest);

		if (entry != null) {
			totalOrigContentSize -= entry.getOrigContentSize();
			entriesMap.remove(entry);
			lruSet.remove(entry);
			checkInvariants();
		}
	}

	public ICacheEntry get(BigInteger digest) {
		ICacheEntry entry = entriesMap.get(digest);

		if (entry != null) {
			processHit(entry);
		}

		return entry;
	}

	protected void processHit(ICacheEntry entry) {
		entry.updateUsageTime();
		lruSet.remove(entry);
		lruSet.add(entry);
	}

	public int getVersion() {
		return VERSION;
	}

	public static DefaultCacheIndex read(DataInputStream dis) throws IOException {
		DefaultCacheIndex result = new DefaultCacheIndex();
		result.readData(dis);
		return result;
	}

	public long getTotalOrigContentSize() {
		return totalOrigContentSize;
	}

	public Iterator<ICacheEntry> getEntriesByAge() {
		return lruSet.iterator();
	}

	public ICacheEntry createNewEntry(DigestInfo digestInfo, File contentDirectory) throws IOException {
		String entryName = "entry_" + digestInfo.getDigest().toString(16);
		File location = new File(contentDirectory, entryName);
		CacheUtil.deleteFileOrDirectory(location);
		CacheUtil.mkdir(location);
		File relativeLocation = new File(entryName);
		return new DefaultCacheEntry(digestInfo.getDigest(), digestInfo.getSourceLength(), relativeLocation);
	}

	protected void readData(DataInputStream in) throws IOException {
		int version = in.readInt();

		if (version != VERSION) {
			throw new IOException("Version of model cache index is unexpected.  Expected: " + VERSION + " but got: "
					+ version);
		}

		int size = in.readInt();

		allocateBasicStructures(size);

		for (int i = 0; i < size; ++i) {
			ICacheEntry entry = DefaultCacheEntry.read(in);

			lruSet.add(entry);
			entriesMap.put(entry.getDigest(), entry);
			totalOrigContentSize += entry.getOrigContentSize();
		}

		checkInvariants();
	}

	public void write(DataOutputStream out) throws IOException {
		out.writeInt(VERSION);
		out.writeInt(lruSet.size());
		Iterator<ICacheEntry> iterator = lruSet.iterator();

		while (iterator.hasNext()) {
			ICacheEntry entry = iterator.next();
			entry.write(out);
		}
	}

	private void checkInvariants() {
		if (!LOGGER.isDebugEnabled()) {
			return; 
		}
		
		Collection<ICacheEntry> entries = entriesMap.values();

		long size = 0;
		for (ICacheEntry entry : entries) {
			size += entry.getOrigContentSize();
		}

		if (size != totalOrigContentSize) {
			throw new IllegalStateException("The size maintained by the model cache (" + totalOrigContentSize
					+ ") does not correspond to the calculated size (" + size + ").");
		}

		final int entriesMapSize = entriesMap.size();
		final int lruSetSize = lruSet.size();

		if (lruSetSize != entriesMapSize) {
			throw new IllegalStateException("The number of entries maintained in the entriesMap (" + entriesMapSize
					+ ") is not the same as the number of entries in the lruSet (" + lruSetSize + ").");
		}

		for (ICacheEntry entry : lruSet) {
			if (!entriesMap.containsKey(entry.getDigest())) {
				throw new IllegalStateException("The lruSet contains an item that is not in the entriesMap.");
			}
		}
	}
}
