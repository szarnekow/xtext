/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.generator.grammarAccess;

import static org.eclipse.xtext.generator.grammarAccess.UnicodeCharacterDatabaseNames.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class UnicodeCharacterDatabaseNamesTest extends Assert {
	
	@Test public void testGetName() throws Exception {
		assertEquals("LATIN SMALL LETTER U WITH DIAERESIS",getCharacterName('�'));
		assertEquals("LATIN SMALL LETTER A WITH DIAERESIS",getCharacterName('�'));
		assertEquals("DIGIT NINE",getCharacterName('9'));
		assertEquals("CJK UNIFIED IDEOGRAPH-4711",getCharacterName('\u4711'));
	}
	
	@Test public void testSmoke() throws Exception {
		for (int i = 0; i < 8000; i++) {
			String characterName = getCharacterName((char)i);
			assertNotNull("Character : "+((char)i),characterName);
		}
	}
	
}
