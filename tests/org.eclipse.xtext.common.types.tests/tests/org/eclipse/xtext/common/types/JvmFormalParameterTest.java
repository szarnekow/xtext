/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JvmFormalParameterTest extends Assert {

	private JvmFormalParameter formalParameter;

	@Before
	public void setUp() throws Exception {
		formalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
	}
	
	@Test public void testGetIdentifier_01() {
		assertNull(formalParameter.getIdentifier());
	}
	
//	@Test public void testGetIdentifier_02() {
//		formalParameter.setName("param");
//		assertNull(formalParameter.getCanonicalName());
//	}
	
//	@Test public void testGetIdentifier_03() {
//		JvmTypeParameter typeParameter = TypesFactory.eINSTANCE.createJvmTypeParameter();
//		typeParameter.setName("T");
//		formalParameter.setName("param");
//		JvmParameterizedTypeReference typeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
//		typeReference.setType(typeParameter);
//		formalParameter.setParameterType(typeReference);
//		assertEquals("T", formalParameter.getCanonicalName());
//	}
	
	@Test public void testToString_01() {
		assertNotNull("toString() should not throw NPE and not return null", formalParameter.toString());
	}
}
