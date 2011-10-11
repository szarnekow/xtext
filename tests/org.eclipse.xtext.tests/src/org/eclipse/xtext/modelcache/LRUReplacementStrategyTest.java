/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.xtext.resource.cache.DefaultModelCacheIndex;
import org.eclipse.xtext.resource.cache.DigestInfo;
import org.eclipse.xtext.resource.cache.ICacheEntry;
import org.eclipse.xtext.resource.cache.IModelCacheIndex;
import org.eclipse.xtext.resource.cache.LRUReplacementStrategy;
import org.eclipse.xtext.resource.cache.Util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class LRUReplacementStrategyTest extends TestCase {
	private static final long MAXSIZE = 10;

	public void testLRU() throws IOException {
		LRUReplacementStrategy strategy = new LRUReplacementStrategy(MAXSIZE);
		IModelCacheIndex index = new DefaultModelCacheIndex();
		File contentDirectory = File.createTempFile("test", "");
		Util.deleteFileOrDirectory(contentDirectory);
		Util.mkdir(contentDirectory);
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

		{
			index.add(entries.get(0));
			ICacheEntry newEntry = createEntry(index, contentDirectory, i);
			entries.add(newEntry); 
			i++;
			ImmutableList<ICacheEntry> candidates = strategy.selectReplacementCandidates(index, newEntry);
			assertEquals (2, candidates.size()); 
			assertTrue (candidates.contains(entries.get (1)));
			assertTrue (candidates.contains(entries.get (2)));
		}

	}

	protected ICacheEntry createEntry(IModelCacheIndex index, File contentDirectory, int i) throws IOException {
		String content = Character.toString((char) i);
		DigestInfo digest = Util.calcDigest(content);
		ICacheEntry entry = index.createNewEntry(digest, contentDirectory);
		return entry;
	}
}
