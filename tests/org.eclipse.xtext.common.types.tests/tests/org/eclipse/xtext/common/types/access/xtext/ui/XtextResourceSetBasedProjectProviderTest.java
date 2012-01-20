/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.xtext.ui;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.common.types.access.jdt.MockJavaProjectProvider;
import org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextResourceSetBasedProjectProviderTest extends Assert {

	private XtextResourceSet resourceSet;
	private MockJavaProjectProvider mockProjectProvider;
	private XtextResourceSetBasedProjectProvider projectProvider;

	@Before
	public void setUp() throws Exception {
		resourceSet = new XtextResourceSet();
		mockProjectProvider = new MockJavaProjectProvider();
		resourceSet.setClasspathURIContext(mockProjectProvider.getJavaProject(null));
		projectProvider = new XtextResourceSetBasedProjectProvider();
	}
	
	@After
	public void tearDown() throws Exception {
		mockProjectProvider = null;
		projectProvider = null;
		resourceSet = null;
	}
	
	@Test public void testSetup() {
		assertNotNull(resourceSet.getClasspathURIContext());
		assertTrue(resourceSet.getClasspathURIContext() instanceof IJavaProject);
	}
	
	@Test public void testGetJavaProject_01() {
		assertNull(projectProvider.getJavaProject(null));
	}
	
	@Test public void testGetJavaProject_02() {
		assertNull(projectProvider.getJavaProject(new ResourceSetImpl()));
	}
	
	@Test public void testGetJavaProject_03() {
		assertNull(projectProvider.getJavaProject(new XtextResourceSet()));
	}
	
	@Test public void testGetJavaProject_04() {
		assertSame(resourceSet.getClasspathURIContext(), projectProvider.getJavaProject(resourceSet));
	}
}
