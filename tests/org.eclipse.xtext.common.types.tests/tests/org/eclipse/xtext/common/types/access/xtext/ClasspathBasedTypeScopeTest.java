/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.xtext;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.common.types.xtext.ClasspathBasedTypeScope;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicates;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ClasspathBasedTypeScopeTest extends AbstractTypeScopeTest {

	private ClasspathTypeProviderFactory factory;
	private ResourceSet resourceSet;
	private ClasspathBasedTypeScope typeScope;

	@Before
	public void setUp() throws Exception {
		factory = new ClasspathTypeProviderFactory(getClass().getClassLoader());
		resourceSet = new ResourceSetImpl();
		typeScope = new ClasspathBasedTypeScope(factory.createTypeProvider(resourceSet), new IQualifiedNameConverter.DefaultImpl(),Predicates.<IEObjectDescription>alwaysTrue());
	}
	
	@Test public void testGetContents_01() {
		try {
			typeScope.getAllElements();
			fail("expected UnsupportedOperationException");
		} catch(UnsupportedOperationException e) {
			// ok
		}
	}

	@Override
	protected IScope getTypeScope() {
		return typeScope;
	}
}
