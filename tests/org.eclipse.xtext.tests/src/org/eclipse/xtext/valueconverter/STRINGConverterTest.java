/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.valueconverter;

import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.STRINGValueConverter;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class STRINGConverterTest extends AbstractXtextTests {

	private STRINGValueConverter valueConverter;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(XtextStandaloneSetup.class);
		valueConverter = get(STRINGValueConverter.class);
		valueConverter.setRule(GrammarUtil.findRuleForName(getGrammarAccess().getGrammar(), "STRING"));
	}

	@Test public void testEscapeChars() throws Exception {
		String s = "\"\\t\\n\\r\\f\\b\"";
		String value = valueConverter.toValue(s, null);
		assertEquals("\t\n\r\f\b", value);
		assertEquals(s, valueConverter.toString(value));
	}

	@Test public void testUnicode() throws Exception {
		String legalString = "\"\\\\u0000\"";
		String value = valueConverter.toValue(legalString, null);
		assertEquals("\\u0000", value);
		assertEquals(legalString, valueConverter.toString(value));
	}
	
	@Test public void testUnicode_01() throws Exception {
		String value = valueConverter.toValue("\"\\u0001\"", null);
		assertEquals("\u0001", value);
		assertEquals("\"\u0001\"", valueConverter.toString(value));
	}
	
	@Test public void testUnicode_02() throws Exception {
		String value = "\u1234";
		String string = valueConverter.toString(value);
		assertEquals(value, valueConverter.toValue(string, null));
	}
	
	@Test public void testUnicodeSequenceLength() throws Exception {
		try {
			valueConverter.toValue("'\\u123'", null);
			fail("Illegal short unicode sequence not detected");
		} catch(ValueConverterException e) {
			// normal operation
		}
		assertEquals("\u1234", valueConverter.toValue("'\\u1234'", null));
	}
	
	@Test public void testEmpty() throws Exception {
		assertEquals("\"\"", valueConverter.toString(""));
	}

	@Test public void testNull() throws Exception {
		try {
			valueConverter.toString(null);
			fail("Null value not detected.");
		} catch (ValueConverterException e) {
			// normal operation
//			System.out.println(e.getMessage());
		}
	}


}
