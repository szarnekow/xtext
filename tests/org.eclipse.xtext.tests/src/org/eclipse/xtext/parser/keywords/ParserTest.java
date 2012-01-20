/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parser.keywords;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.parser.ParserTestHelper;
import org.eclipse.xtext.parser.keywords.keywordsTestLanguage.KeywordsTestLanguagePackage;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ParserTest extends AbstractXtextTests {

	private ParserTestHelper helper;
	private EStructuralFeature first;
	private EStructuralFeature second;
	private EStructuralFeature third;
	private EStructuralFeature forth;
	private EStructuralFeature fifth;
	private EStructuralFeature sixth;
	private EStructuralFeature seventh;
	private EStructuralFeature eighth;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(KeywordsTestLanguageStandaloneSetup.class);
		helper = new ParserTestHelper(getResourceFactory(), getParser(), get(Keys.RESOURCE_SET_KEY),getCurrentFileExtension());
		EPackage pack = KeywordsTestLanguagePackage.eINSTANCE;
		EClass clazz = (EClass) pack.getEClassifier("Model");
		first = clazz.getEStructuralFeature("first");
		second = clazz.getEStructuralFeature("second");
		third = clazz.getEStructuralFeature("third");
		forth = clazz.getEStructuralFeature("forth");
		fifth = clazz.getEStructuralFeature("fifth");
		sixth = clazz.getEStructuralFeature("sixth");
		seventh = clazz.getEStructuralFeature("seventh");
		eighth = clazz.getEStructuralFeature("eighth");
	}

	private void checkModel(XtextResource resource, EStructuralFeature feature) {
		EObject model = resource.getParseResult().getRootASTElement();
		assertNotNull(model);
		boolean value = (Boolean) model.eGet(feature);
		assertTrue(value);
	}

	@Test public void testFooBar() throws Exception {
		XtextResource resource = helper.getResourceFromString("foo\\bar");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, first);
	}

	@Test public void testFoo() throws Exception {
		XtextResource resource = helper.getResourceFromString("foo\\");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, second);
	}

	@Test public void testBar() throws Exception {
		XtextResource resource = helper.getResourceFromString("\\bar");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, third);
	}

	@Test public void testBackslash() throws Exception {
		XtextResource resource = helper.getResourceFromString("\\");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, forth);
	}

	@Test public void testFifth() throws Exception {
		XtextResource resource = helper.getResourceFromString("\"a\"");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, fifth);
	}
	
	@Test public void testSixth() throws Exception {
		XtextResource resource = helper.getResourceFromString("'b'");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, sixth);
	}
	
	@Test public void testSeventh() throws Exception {
		XtextResource resource = helper.getResourceFromString("'c'");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, seventh);
	}
	
	@Test public void testEighth() throws Exception {
		XtextResource resource = helper.getResourceFromString("\"d\"");
		assertTrue(resource.getErrors().isEmpty());
		checkModel(resource, eighth);
	}
}
