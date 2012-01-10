/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.impl;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ClassURIHelperTest extends Assert {

	private ClassURIHelper uriHelper;

	@Before
	public void setUp() throws Exception {
		uriHelper = new ClassURIHelper();
	}
	
	@After
	public void tearDown() throws Exception {
		uriHelper = null;
	}
	
	@Test public void testCreateResourceURI_01() {
		URI uri = uriHelper.createResourceURI(Integer.TYPE);
		assertEquals("java:/Primitives", uri.toString());
	}
	
	@Test public void testCreateResourceURI_02() {
		URI uri = uriHelper.createResourceURI(String.class);
		assertEquals("java:/Objects/java.lang.String", uri.toString());
	}
	
	@Test public void testCreateResourceURI_03() {
		URI uri = uriHelper.createResourceURI(String[].class);
		assertEquals("java:/Objects/java.lang.String", uri.toString());
	}
	
	@Test public void testCreateResourceURI_04() {
		URI uri = uriHelper.createResourceURI(int[].class);
		assertEquals("java:/Primitives", uri.toString());
	}
	
	@Test public void testCreateResourceURI_05() {
		URI uri = uriHelper.createResourceURI(Map.Entry.class);
		assertEquals("java:/Objects/java.util.Map", uri.toString());
	}
	
	@Test public void testCreateResourceURI_06() {
		URI uri = uriHelper.createResourceURI(Map.Entry[].class);
		assertEquals("java:/Objects/java.util.Map", uri.toString());
	}
	
	@Test public void testCreateResourceURI_07() {
		URI uri = uriHelper.createResourceURI("Something");
		assertEquals("java:Something", uri.toString());
	}
	
	@Test public void testFragmentURI_01() {
		String fragment = uriHelper.getFragment(int.class);
		assertEquals(int.class.getName(), fragment);
	}
	
	@Test public void testFragmentURI_02() {
		String fragment = uriHelper.getFragment(String.class);
		assertEquals(String.class.getName(), fragment);
	}
	
	@Test public void testFragmentURI_03() {
		String fragment = uriHelper.getFragment(int[].class);
		assertEquals("int[]", fragment);
	}
	
	@Test public void testFragmentURI_04() {
		String fragment = uriHelper.getFragment(String[].class);
		assertEquals(String.class.getName() + "[]", fragment);
	}

	@Test public void testFragmentURI_05() {
		String fragment = uriHelper.getFragment(Map.Entry.class);
		assertEquals(Map.Entry.class.getName(), fragment);
	}
	
	@Test public void testFragmentURI_06() {
		String fragment = uriHelper.getFragment(Map.Entry[].class);
		assertEquals(Map.Entry.class.getName() + "[]", fragment);
	}
	
}
