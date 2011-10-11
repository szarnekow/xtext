/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.xtext.resource.cache.CacheUtil;
import org.eclipse.xtext.resource.cache.DefaultCacheIndex;
import org.eclipse.xtext.resource.cache.DigestInfo;
import org.eclipse.xtext.resource.cache.ICacheEntry;
import org.eclipse.xtext.resource.cache.ICacheIndex;
import org.eclipse.xtext.resource.cache.LRUReplacementStrategy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class LRUReplacementStrategyTest extends TestCase {
	private static final long MAXSIZE = 10;

	public void testLRU() throws IOException {
		LRUReplacementStrategy strategy = new LRUReplacementStrategy(MAXSIZE);
		ICacheIndex index = new DefaultCacheIndex();
		File contentDirectory = File.createTempFile("test", "");
		CacheUtil.deleteFileOrDirectory(contentDirectory);
		CacheUtil.mkdir(contentDirectory);
		List<ICacheEntry> entries = Lists.newArrayList();

		int i = 0;
		for (; i < MAXSIZE + 1; ++i) {
			ICacheEntry entry = createEntry(index, contentDirectory, i);
			entries.add(entry);
			ImmutableList<ICacheEntry> candidates = strategy.selectReplacementCandidates(index, entry);
			index.add(entry);
			if (i < MAXSIZE) {
				assertTrue(candidates.isEmpty());
			} else {
				assertEquals(1, candidates.size());
				assertEquals(entries.get(0), candidates.get(0));
			}
		}
		
		assertEquals(MAXSIZE+1, Iterators.size(index.getEntriesByAge())); 
		assertEquals(MAXSIZE+1, index.getTotalOrigContentSize());

		{
			index.add(entries.get(0));
			ICacheEntry newEntry = createEntry(index, contentDirectory, i);
			entries.add(newEntry); 
			i++;
			ImmutableList<ICacheEntry> candidates = strategy.selectReplacementCandidates(index, newEntry);
			assertEquals (3, candidates.size()); 
			assertTrue (candidates.contains(entries.get (1)));
			assertTrue (candidates.contains(entries.get (2)));
		}

	}

	protected ICacheEntry createEntry(ICacheIndex index, File contentDirectory, int i) throws IOException {
		String content = Character.toString((char) i);
		assertEquals(1, content.length()); 
		DigestInfo digest = CacheUtil.calcDigest(content);
		ICacheEntry entry = index.createNewEntry(digest, contentDirectory);
		return entry;
	}
}
