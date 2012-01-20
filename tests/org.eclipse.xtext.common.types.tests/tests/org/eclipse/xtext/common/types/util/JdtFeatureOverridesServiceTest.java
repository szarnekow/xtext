/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.util;

import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.access.jdt.MockJavaProjectProvider;
import org.eclipse.xtext.common.types.tests.JdtBasedModule;

import com.google.inject.Module;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class JdtFeatureOverridesServiceTest extends AbstractFeatureOverridesServiceTest {

	@Override
	protected Module getModule() {
		return new JdtBasedModule(){
			@Override
			public Class<? extends IJavaProjectProvider> bindIJavaProjectProvider() {
				return MockJavaProjectProvider.class;
			}
		};
	}

	//TODO disabled since types are not visible on the Mock project
//	@Override
//	@Test public void testContainsFields() throws Exception {
//	}
//	
//	@Override
//	@Test public void testGenerics_00() throws Exception {
//	}
//	
//	@Override
//	@Test public void testGenerics_01() throws Exception {
//	}
}
