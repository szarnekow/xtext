/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.builderState;

/**
 * Will fail for EMF 2.6 or higher. See {@link BufferedBinaryResourceFactory} for details.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class BinaryResourceBasedPersisterTest extends AbstractEMFBasedPersisterTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		persister.setFactory(new BufferedBinaryResourceFactory());
	}
	
}
