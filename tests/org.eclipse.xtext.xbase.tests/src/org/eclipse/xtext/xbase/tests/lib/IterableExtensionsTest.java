/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.tests.lib;

import static com.google.common.collect.Lists.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class IterableExtensionsTest extends BaseIterablesIteratorsTest<Iterable<Integer>> {

	@Override
	protected Iterable<Integer>[] testData(Integer... elements) {
		@SuppressWarnings("unchecked")
		Iterable<Integer>[] result = new Iterable[] {
			Lists.newArrayList(elements),
			Lists.newLinkedList(Lists.newArrayList(elements)),
			Sets.newLinkedHashSet(Lists.newArrayList(elements)),
			Sets.newTreeSet(Lists.newArrayList(elements))
		};
		return result;
	}

	@Override
	protected Iterable<Integer> dummy() {
		return Collections.emptyList();
	}

	@Override
	protected boolean is(Iterable<Integer> input, Integer... elements) {
		return Iterables.elementsEqual(input, Lists.newArrayList(elements));
	}

	@Override
	protected Iterable<Integer> operator_plus(Iterable<Integer> first, Iterable<Integer> second) {
		return IterableExtensions.operator_plus(first, second);
	}

	@Override
	protected Integer findFirst(Iterable<Integer> input, Function1<Integer, Boolean> filter) {
		return IterableExtensions.findFirst(input, filter);
	}

	@Override
	protected Integer findLast(Iterable<Integer> input, Function1<Integer, Boolean> filter) {
		return IterableExtensions.findLast(input, filter);
	}

	@Override
	protected Integer last(Iterable<Integer> input) {
		return IterableExtensions.last(input);
	}

	@Override
	protected Integer head(Iterable<Integer> input) {
		return IterableExtensions.head(input);
	}
	
	@Test public void testJoinWithNull() {
		List<String> list = Lists.newArrayList("a", null, "c");
		String string = IterableExtensions.join(list, ",");
		assertEquals("a,null,c", string);
	}
	
	@Test public void testSortBy() throws Exception {
		List<? extends CharSequence> list = newArrayList("foo","bar","baz");
		List<? extends CharSequence> sorted = IterableExtensions.sortBy(list, new Functions.Function1<CharSequence, String>() {
			public String apply(CharSequence p) {
				return p.toString();
			}
		});
		assertNotSame(list, sorted);
		assertEquals(sorted, newArrayList("bar","baz","foo"));
	}
	
	@Test public void testFilterNull() throws Exception {
		Iterator<String> iter = IterableExtensions.filterNull(newArrayList("foo", null, "bar")).iterator();
		assertEquals("foo", iter.next());
		assertEquals("bar", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test public void testJoinWithBeforeAndAfter() throws Exception {
		ArrayList<String> list = newArrayList("foo", "bar");
		ArrayList<String> singletonList = newArrayList("foo");
		ArrayList<String> emptylist = new ArrayList<String>();
		
		final Functions.Function1<String, String> function = new Functions.Function1<String, String>() {
			public String apply(String p) {
				return p;
			}
		};
		assertEquals("<foo,bar>", IterableExtensions.join(list, "<", ",", ">", function));
		assertEquals("<foo>", IterableExtensions.join(singletonList, "<", ",", ">", function));
		assertEquals("", IterableExtensions.join(emptylist, "<", ",", ">", function));
		
		assertEquals("foo,bar>", IterableExtensions.join(list, null, ",", ">", function));
		assertEquals("foo>", IterableExtensions.join(singletonList, null, ",", ">", function));
		assertEquals("", IterableExtensions.join(emptylist, null, ",", ">", function));
		
		assertEquals("<foobar>", IterableExtensions.join(list, "<", null, ">", function));
		assertEquals("<foo>", IterableExtensions.join(singletonList, "<", null, ">", function));
		assertEquals("", IterableExtensions.join(emptylist, "<", null, ">", function));
		
		assertEquals("<foo,bar", IterableExtensions.join(list, "<", ",", null, function));
		assertEquals("<foo", IterableExtensions.join(singletonList, "<", ",", null, function));
		assertEquals("", IterableExtensions.join(emptylist, "<", ",", null, function));
	}

	
}
