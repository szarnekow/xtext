/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.IOException;
import java.util.List;

import org.eclipse.xtext.util.StringInputStream;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class LRUReplacementStrategyTest extends Assert {
	private static final long MAXSIZE = 10;

	@Test
	public void testLRU() throws IOException {
		LRUReplacementStrategy strategy = new LRUReplacementStrategy(MAXSIZE);
		ICacheIndex index = new DefaultCacheIndex();
		List<ICacheEntry> entries = Lists.newArrayList();

		int i = 0;
		for (; i < MAXSIZE + 1; ++i) {
			ICacheEntry entry = createEntry(index, i);
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
			index.get(entries.get(0).getDigest());
			ICacheEntry newEntry = createEntry(index, i);
			entries.add(newEntry); 
			i++;
			ImmutableList<ICacheEntry> candidates = strategy.selectReplacementCandidates(index, newEntry);
			assertEquals (2, candidates.size()); 
			assertTrue (candidates.contains(entries.get (1)));
			assertTrue (candidates.contains(entries.get (2)));
		}
	}

	protected ICacheEntry createEntry(ICacheIndex index, int i) throws IOException {
		String content = Character.toString((char) i);
		assertEquals(1, content.length()); 
		DigestInfo digest = CacheUtil.calcDigestInfo(new StringInputStream(content), "UTF-8");
		ICacheEntry entry = index.createNewEntry(digest);
		return entry;
	}
}
