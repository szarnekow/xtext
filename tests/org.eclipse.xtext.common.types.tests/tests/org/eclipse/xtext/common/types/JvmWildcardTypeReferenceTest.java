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
public class JvmWildcardTypeReferenceTest extends Assert {

	private JvmWildcardTypeReference typeArgument;

	@Before
	public void setUp() throws Exception {
		typeArgument = TypesFactory.eINSTANCE.createJvmWildcardTypeReference();
	}
	
	@Test public void testGetIdentifier_01() {
		assertEquals("?", typeArgument.getIdentifier());
	}
	
}
