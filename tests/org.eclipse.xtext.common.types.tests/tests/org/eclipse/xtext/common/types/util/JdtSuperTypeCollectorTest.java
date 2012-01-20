/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.util;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.MockJavaProjectProvider;
import org.junit.After;
import org.junit.Before;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JdtSuperTypeCollectorTest extends AbstractSuperTypeCollectorTest {

	private ResourceSet resourceSet;
	private JdtTypeProvider typeProvider;
	private MockJavaProjectProvider projectProvider;

	@Before
	public void setUp() throws Exception {
		resourceSet = new ResourceSetImpl();
		projectProvider = new MockJavaProjectProvider();
		typeProvider = new JdtTypeProvider(projectProvider.getJavaProject(resourceSet), resourceSet);
	}
	
	@After
	public void tearDown() throws Exception {
		resourceSet = null;
		typeProvider = null;
	}
	
	@Override
	protected JdtTypeProvider getTypeProvider() {
		return typeProvider;
	}
}
