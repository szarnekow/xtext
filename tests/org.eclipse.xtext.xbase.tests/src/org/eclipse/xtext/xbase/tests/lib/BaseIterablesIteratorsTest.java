/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.tests.lib;

import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class BaseIterablesIteratorsTest<IterableOrIterator> extends Assert {

	protected Integer first = Integer.valueOf(1);
	protected Integer second = Integer.valueOf(2);
	protected Integer third = Integer.valueOf(3);
	protected Integer forth = Integer.valueOf(4);
	protected Integer fifth = Integer.valueOf(5);
	
	protected abstract IterableOrIterator[] testData(Integer... elements);
	protected abstract IterableOrIterator dummy();
	protected abstract boolean is(IterableOrIterator input, Integer... elements);

	protected abstract IterableOrIterator operator_plus(IterableOrIterator first, IterableOrIterator second);
	protected boolean canIterateTwice() {
		return true;
	}
	
	@Test public void testOperatorPlus_Same() {
		IterableOrIterator[] data = testData(first, second);
		for(int i = 0;i < data.length; i++) {
			if (canIterateTwice())
				assertTrue(is(operator_plus(data[i], data[i]), first, second, first, second));
			else
				assertTrue(is(operator_plus(data[i], data[i]), first, second));
		}
	}
	
	@Test public void testOperatorPlus() {
		IterableOrIterator[] firstData = testData(first, second);
		IterableOrIterator[] secondData = testData(third, forth);
		for(int i = 0;i < firstData.length; i++) {
			assertTrue(is(operator_plus(firstData[i], secondData[i]), first, second, third, forth));
		}
	}
	
	@Test public void testOperatorPlus_NPE_left() {
		try {
			operator_plus(null, dummy());
			fail("expected NullPointerException");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
	@Test public void testOperatorPlus_NPE_right() {
		try {
			operator_plus(dummy(), null);
			fail("expected NullPointerException");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
	protected abstract Integer findFirst(IterableOrIterator input, Functions.Function1<Integer, Boolean> filter);
	
	@Test public void testFindFirst_empty() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return true;
			}
		};
		for(IterableOrIterator testMe: testData()) {
			Integer last = findFirst(testMe, filter);
			assertNull("empty input yields null", last);
		}
	}
	
	@Test public void testFindFirst_noMatch() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return false;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findFirst(testMe, filter);
			assertNull("unmatched input yields null", last);
		}
	}
	
	@Test public void testFindFirst_allMatches() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return true;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findFirst(testMe, filter);
			assertEquals(first, last);
		}
	}
	
	@Test public void testFindFirst_oneMatch() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return second.equals(p);
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findFirst(testMe, filter);
			assertEquals(second, last);
		}
	}

	@Test public void testFindFirst_exceptionInFilter() {
		final RuntimeException expectedException = new RuntimeException();
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				throw expectedException;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			try {
				findFirst(testMe, filter);
				fail("expected exception");
			} catch(RuntimeException e) {
				assertSame(expectedException, e);
			}
		}
	}
	
	@Test public void testFindFirst_exceptionInFilter_emptyInput() {
		final RuntimeException expectedException = new RuntimeException();
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				throw expectedException;
			}
		};
		for(IterableOrIterator testMe: testData()) {
			assertNull(findFirst(testMe, filter));
		}
	}
	
	@Test public void testFindFirst_NPE_noFilter() {
		for(IterableOrIterator testMe: testData()) {
			try {
				findFirst(testMe, null);
				fail("Expected NPE");
			} catch(NullPointerException npe) {
				// expected
			}
		}
	}
	
	@Test public void testFindFirst_NPE_noInput() {
		try {
			findLast(null, new Functions.Function1<Integer, Boolean>() {
				public Boolean apply(Integer p) {
					return true;
				}
			});
			fail("Expected NPE");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
	protected abstract Integer findLast(IterableOrIterator input, Functions.Function1<Integer, Boolean> filter);
	
	@Test public void testFindLast_empty() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return true;
			}
		};
		for(IterableOrIterator testMe: testData()) {
			Integer last = findLast(testMe, filter);
			assertNull("empty input yields null", last);
		}
	}
	
	@Test public void testFindLast_noMatch() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return false;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findLast(testMe, filter);
			assertNull("unmatched input yields null", last);
		}
	}
	
	@Test public void testFindLast_allMatches() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return true;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findLast(testMe, filter);
			assertEquals(third, last);
		}
	}
	
	@Test public void testFindLast_oneMatch() {
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				return second.equals(p);
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = findLast(testMe, filter);
			assertEquals(second, last);
		}
	}

	@Test public void testFindLast_exceptionInFilter() {
		final RuntimeException expectedException = new RuntimeException();
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				throw expectedException;
			}
		};
		for(IterableOrIterator testMe: testData(first, second, third)) {
			try {
				findLast(testMe, filter);
				fail("expected exception");
			} catch(RuntimeException e) {
				assertSame(expectedException, e);
			}
		}
	}
	
	@Test public void testFindLast_exceptionInFilter_emptyInput() {
		final RuntimeException expectedException = new RuntimeException();
		Function1<Integer, Boolean> filter = new Functions.Function1<Integer, Boolean>() {
			public Boolean apply(Integer p) {
				throw expectedException;
			}
		};
		for(IterableOrIterator testMe: testData()) {
			Integer last = findLast(testMe, filter);
			assertEquals(null, last);
		}
	}
	
	@Test public void testFindLast_NPE_noFilter() {
		for(IterableOrIterator testMe: testData()) {
			try {
				findLast(testMe, null);
				fail("Expected NPE");
			} catch(NullPointerException npe) {
				// expected
			}
		}
	}
	
	@Test public void testFindLast_NPE_noInput() {
		try {
			findLast(null, new Functions.Function1<Integer, Boolean>() {
				public Boolean apply(Integer p) {
					return true;
				}
			});
			fail("Expected NPE");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
	protected abstract Integer last(IterableOrIterator input);
	
	@Test public void testLast_empty() {
		for(IterableOrIterator testMe: testData()) {
			Integer last = last(testMe);
			assertNull("empty input yields null", last);
		}
	}
	
	@Test public void testLast_oneEntry() {
		for(IterableOrIterator testMe: testData(first)) {
			Integer last = last(testMe);
			assertEquals(first, last);
		}
	}
	
	@Test public void testLast_entryIsNull() {
		for(IterableOrIterator testMe: testData((Integer)null)) {
			Integer last = last(testMe);
			assertEquals(null, last);
		}
	}
	
	@Test public void testLast_moreEntries() {
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer last = last(testMe);
			assertEquals(third, last);
		}
	}
	
	@Test public void testLast_NPE() {
		try {
			last(null);
			fail("expeced NPE");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
	protected abstract Integer head(IterableOrIterator input);
	
	@Test public void testHead_empty() {
		for(IterableOrIterator testMe: testData()) {
			Integer head = head(testMe);
			assertNull("empty input yields null", head);
		}
	}
	
	@Test public void testHead_oneEntry() {
		for(IterableOrIterator testMe: testData(first)) {
			Integer head = head(testMe);
			assertEquals(first, head);
		}
	}
	
	@Test public void testHead_entryIsNull() {
		for(IterableOrIterator testMe: testData((Integer)null)) {
			Integer head = head(testMe);
			assertEquals(null, head);
		}
	}
	
	@Test public void testHead_moreEntries() {
		for(IterableOrIterator testMe: testData(first, second, third)) {
			Integer head = head(testMe);
			assertEquals(first, head);
		}
	}
	
	@Test public void testHead_NPE() {
		try {
			head(null);
			fail("expeced NPE");
		} catch(NullPointerException npe) {
			// expected
		}
	}
	
}
