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
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider.Factory;
import org.eclipse.xtext.common.types.tests.ClasspathBasedModule;
import org.junit.After;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ClasspathSuperTypeCollectorTest extends AbstractSuperTypeCollectorTest {

	private ResourceSet resourceSet;
	private IJvmTypeProvider typeProvider;

	@Before
	public void setUp() throws Exception {
		resourceSet = new ResourceSetImpl();
		Injector injector = Guice.createInjector(new ClasspathBasedModule());
		Factory instance = injector.getInstance(IJvmTypeProvider.Factory.class);
		typeProvider = instance.findOrCreateTypeProvider(resourceSet);
	}
	
	@After
	public void tearDown() throws Exception {
		resourceSet = null;
		typeProvider = null;
	}
	
	@Override
	protected IJvmTypeProvider getTypeProvider() {
		return typeProvider;
	}
}
