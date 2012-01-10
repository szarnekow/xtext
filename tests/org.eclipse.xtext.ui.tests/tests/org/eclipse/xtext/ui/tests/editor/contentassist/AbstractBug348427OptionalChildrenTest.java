/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.junit.Test;


/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class AbstractBug348427OptionalChildrenTest extends AbstractBug348427Test {

	@Override
	@Test public void testExpectName() throws Exception {
		newBuilder().assertText("Name", "next");
	}
	
}
