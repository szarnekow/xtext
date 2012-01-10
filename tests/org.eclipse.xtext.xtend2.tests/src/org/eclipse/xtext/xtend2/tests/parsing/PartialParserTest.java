/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.tests.parsing;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.EmfFormatter;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.validation.ResourceValidatorImpl;
import org.eclipse.xtext.xtend2.tests.AbstractXtend2TestCase;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class PartialParserTest extends AbstractXtend2TestCase {
	
	@Inject
	private InvariantChecker invariantChecker;
	
	@Test public void testNoClassCastException() throws Exception {
		String model =
				"package org.eclipse.xtext.xtend2.tests.parsing\n" + 
				"\n" + 
				"class NoClassCastException {\n" + 
				"\n" + 
				"	String foo(String a, String b) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'holla')";
		doTestUpdateAtEnd(model, '\n', "NoClassCastException.xtend");
	}
	
	@Test public void testSyntaxErrorOnLeafNode_01() throws Exception {
		String model =
			"package org.eclipse.xtext.xtend2.tests.parsing\n" + 
			"\n" + 
			"class SyntaxErrorOnLeafNode {\n" + 
			"\n" + 
			"	String foo(String a, String b) {\n" + 
			"		if (isUpper(a)) {\n" + 
			"			another(a,b+'holla')\n" + 
			"		} else {\n" + 
			"			val x = a;\n" + 
			"			for (y : b.toCharArray) {\n" + 
			"				x = x+";
		doTestUpdateAtEnd(model, 'y', "SyntaxErrorOnLeafNode.xtend");
	}
	
	@Test public void testSyntaxErrorOnLeafNode_02() throws Exception {
		String model =
			"package org.eclipse.xtext.xtend2.tests.linking\n" + 
			"\n" + 
			"class SyntaxErrorOnLeafNode {\n" + 
			"\n" + 
			"	String foo(String a, String b) {\n" + 
			"		if (isUpper(a)) {\n" + 
			"			another(a,b+'holla')\n" + 
			"		} else {\n" + 
			"			val x = a;\n" + 
			"			for (y : b.toCharArray) {\n" + 
			"				x = x+y\n" + 
			"			}\n" + 
			"			x\n" + 
			"		}\n" + 
			"	}\n" + 
			"	\n" + 
			"	private isUpper(String s) \n" + 
			"		s.";
		doTestUpdateAtEnd(model, 't', "SyntaxErrorOnLeafNode.xtend");
	}

	@Test public void testErroneousModelRemoved() throws Exception {
		String model =
				"\n" + 
				"\n" + 
				"class ErroneousModelRemoved {\n" + 
				"\n" + 
				"	String foo(String a, String b) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'holla')\n" + 
				"		} else {\n" + 
				"			val x = a;\n" + 
				"			for (y : b.toCharArray) {\n" + 
				"				x = x+y\n" + 
				"			}\n" + 
				"			x\n" + 
				"		}\n" + 
				"	}\n" + 
				"	\n" + 
				"	private isUpper(String s) \n" + 
				"		s.toUpperCase == s\n" + 
				"		\n" + 
				"	private another(String x, String y) \n" + 
				"		y+x\n" + 
				"	\n" + 
				"\n" + 
				"}";
		XtextResource resource = doTestUpdateAtOffset(model, 0, 0, "g", "ErroneousModelRemoved.xtend");
		assertTrue(resource.getContents().isEmpty());
	}
	
	@Test public void testEqualNodeModels() throws Exception {
		String model =
				"package org.eclipse.xtext.xtend2.tests.parsing\n" + 
				"\n" + 
				"class EqualNodeModels {\n" + 
				"\n" + 
				"	String foo(String a, String b) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'holla')";
		doTestUpdateAtEnd(model, '\n', "EqualNodeModels.xtend");
	}
	
	@Test public void testEqualNodeModels_02() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.linking\n" + 
				"\n" + 
				"class EqualNodeModels {\n" + 
				"\n" + 
				"	String foo(String a, Stringb) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'holla')\n" + 
				"		} else {\n" + 
				"			val x = a;\n" + 
				"			for (y : b.toCharArray) {\n" + 
				"				x = x+y\n" + 
				"			}\n" + 
				"			x\n" + 
				"		}\n" + 
				"	}\n" + 
				"	\n" + 
				"	private isUpper(String s) \n" + 
				"		s.toUpperCase == s\n" + 
				"		\n" + 
				"	private another(String x, String y) \n" + 
				"		y+x\n" + 
				"	\n" + 
				"\n" + 
				"}";
		// offset's origin is the smoke test
		doTestUpdateAtOffset(model, 101, 1, " ", "EqualNodeModels.xtend");
	}
	
	@Test public void testEqualNodeModels_03() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.linking\n" + 
				"\n" + 
				"class EqualNodeModels {\n" + 
				"\n" + 
				"	String foo(String a, String b) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'olla')\n" + 
				"		} else {\n" + 
				"			val x = a;\n" + 
				"			for (y : b.toCharArray) {\n" + 
				"				x = x+y\n" + 
				"			}\n" + 
				"			x\n" + 
				"		}\n" + 
				"	}\n" + 
				"	\n" + 
				"	private isUpper(String s) \n" + 
				"		s.toUpperCase == s\n" + 
				"		\n" + 
				"	private another(String x, String y) \n" + 
				"		y+x\n" + 
				"	\n" + 
				"\n" + 
				"}";
		
		doTestUpdateAtOffset(model, 143, 1, "h", "EqualNodeModels.xtend");
	}
	
	@Test public void testEqualNodeModels_04() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.linking\n" + 
				"\n" + 
				"class EqualNodeModels {\n" + 
				"\n" + 
				"	String foo(String a, String b) {\n" + 
				"		if (isUpper(a)) {\n" + 
				"			another(a,b+'holla')\n" + 
				"		} else {\n" + 
				"			val x = a;\n" + 
				"			for (y : b.toCharArray) {\n" + 
				"				x = x+y\n" + 
				"			}\n" + 
				"			x\n" + 
				"		}\n" + 
				"	}\n" + 
				"	\n" + 
				"	private isUpper(String s) \n" + 
				"		s.toUpperCase== s\n" + 
				"		\n" + 
				"	private another(String x, String y) \n" + 
				"		y+x\n" + 
				"	\n" + 
				"\n" + 
				"}";
		doTestUpdateAtOffset(model, 279, 1, " ", "EqualNodeModels.xtend");
	}
	
	@Test public void testEqualNodeModels_05() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"class EqualNodeModels {\n" + 
				"	testReturnExpression_06() {\n" + 
				"	    val closure = [Integer i| return i]\n" + 
				"	    for (x : 1..100) closure.(x)\n" + 
				"	}\n" + 
				"	testOverriddenLocalVariable() {\n" + 
				"	  val x = 3\n" + 
				"	  var y = 2\n" + 
				"	  {\n" + 
				"	    var x = y\n" + 
				"	    val y = 1\n" + 
				"	    x+y\n" + 
				"	  }\n" + 
				"	}\n" + 
				"	testFeatureCall_03() {\n" + 
				"		{ \n" + 
				"			var java.util.List<Character> this = ('abc'.toCharArray as Iterable<Character>).toList() \n" + 
				"			this \n" + 
				"		}\n" + 
				"	}\n" + 
				"}";
		doTestUpdateAtOffset(model, 170, 1, "apply", "EqualNodeModels.xtend");
	}
	
	@Test public void testEqualModels_01() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"import java.util.ArrayList\n" + 
				"import static java.util.Arrays.*\n" + 
				"import static extension java.util.Collections.*\n" + 
				"\n" + 
				"class Case_5 {\n" + 
				"\n" + 
				"	@Inject\n" + 
				"	ArrayList as myList\n" + 
				"//	ArrayList as list\n" + 
				"	\n" + 
				"	@Inject extension\n" + 
				"	String\n" + 
				"\n" + 
				"	boolean something(int i) {\n" + 
				"	  if (i.indexO() == 0) {\n" + 
				"//	    return list.contains(i)\n" + 
				"	    return myList.contains(i)\n" + 
				"	  } \n" + 
				"	  asList(i)\n" + 
				"	  i.singletonList()\n" + 
				"	  false\n" + 
				"	}\n" + 
				"	\n" + 
				"\n" + 
				"}";
		doTestUpdateAtOffset(model, 295, 1, "f", "Case_5.xtend");
	}
	
	@Test public void testEqualModels_02() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"import org.eclipse.emf.ecore.EClass\n" + 
				"import org.eclipse.emf.ecore.EPackage\n" + 
				"import org.eclipse.emf.ecore.EStructuralFeature\n" + 
				"import org.eclipse.emf.ecore.EObject\n" + 
				"\n" + 
				"class Case_6 {\n" + 
				"\n" + 
				"	dispatch transform(EClass model) {\n" + 
				"		model.ETypeParameters.map(e|transform(e))\n" + 
				"	}\n" + 
				"	 \n" + 
				"	dispatch transform(E";
		doTestUpdateAtOffset(model, 329, 0, "P", "Case_6.xtend");
	}
	
	@Test public void testEqualModels_03() throws Exception {
		String model = "package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"import org.eclipse.emf.ecore.EClass\n" + 
				"import org.eclipse.emf.ecore.EPackage\n" + 
				"import org.eclipse.emf.ecore.EStructuralFeature\n" + 
				"import org.eclipse.emf.ecore.EObject\n" + 
				"\n" + 
				"class Case_6 {\n" + 
				"\n" + 
				"	dispatch transform(EClass model) {\n" + 
				"		model.ETypeParameters.map(|transform(e))\n" + 
				"	}\n" + 
				"	 \n" + 
				"	dispatch transform(EPackage packageDecl) {\n" + 
				"		packageDecl.eContents.map(e|transform(e))\n" + 
				"	}\n" + 
				"	\n" + 
				"	dispatch transform(EStructuralFeature entity) {\n" + 
				"		val inferredType = null\n" + 
				"		newArrayList(inferredType as EObject) 	 \n" + 
				"	}\n" + 
				"\n" + 
				"}";
		doTestUpdateAtOffset(model, 286, 1, "e", "Case_6.xtend");
	}
	
	@Test public void testEqualModels_04() throws Exception {
		String model =
				"package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"class Case_3 {\n" + 
				"	def testReturnExpression_06() {\n" + 
				"	    val closure = [Integer i| return i]\n" + 
				"	    for (x : 1..100) closure.apply(x)\n" + 
				"	}\n" + 
				"	def testReturnExpression_07() {\n" + 
				"		if (typeof(Case_3) != null) {\n" + 
				"		    val (int)=>int closure = [Integer i| return i]\n" + 
				"		    for (x : 1..100) closure.apply(x)\n" + 
				"		}\n" + 
				"	}\n" + 
				"	def testOverriddenLocalVariable() {\n" + 
				"	  val x = 3\n" + 
				"	  var y = 2\n" + 
				"	  {\n" + 
				"	    var x2 = y\n" + 
				"	    val y2 = 1\n" + 
				"	    x2+y2\n" + 
				"	  }\n" + 
				"	}\n" + 
				"	def testFeatureCall_03() {\n" + 
				"		{ \n" + 
				"			var java.util.List<Character> x = ('abc'.toCharArray as Iterable<Character>).toList() \n" + 
				"			x \n" + 
				"		}\n" + 
				"	}\n" + 
				"}";
		assertEquals(229, model.indexOf("typeof(Case_3") + "typeof(Case_3".length());
		XtextResource resource = doTestUpdateAtOffset(model, 229, 1, " ", "Case_3.xtend");
		compareWithNewResource(resource, model, 229, 1, ")", "Case_3.xtend");
		validateWithoutException(resource);
	}
	
	@Test public void testEqualModels_05() throws Exception {
		String model =
				"package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"import org.eclipse.emf.ecore.EClass\n" + 
				"import org.eclipse.emf.ecore.EPackage\n" + 
				"import org.eclipse.emf.ecore.EStructuralFeature\n" + 
				"import org.eclipse.emf.ecore.EObject\n" + 
				"\n" + 
				"class Case_6 {\n" + 
				"\n" + 
				"	def dispatch transform(EClass model) {\n" + 
				"		model.ETypeParameters.map(e|transform(e))\n" + 
				"	}\n" + 
				"	 \n" + 
				"	def dispatch transform(EPackage packageDecl) {\n" + 
				"		packageDecl.eContents.map(e | transform(e as EStructuralFeature))\n" + 
				"	}\n" + 
				"	\n" + 
				"	def dispatch transform(EStructuralFeature entity) {\n" + 
				"		val inferredType = null\n" + 
				"		newArrayList";
		XtextResource resource = doTestUpdateAtOffset(model, 526, 0, "(i", "Case_6.xtend");
		compareWithNewResource(resource, model + "(", 527, 1, "i", "Case_6.xtend");
		validateWithoutException(resource);
	}
	
	@Test public void testInferredModelRemoved() throws Exception {
		String model =
				"package org.eclipse.xtext.xtend2.tests.smoke\n" + 
				"\n" + 
				"classCase_1 {\n" + 
				"\n" + 
				"	testFunction1() {\n" + 
				"		42\n" + 
				"	}\n" + 
				"	\n" + 
				"	testFunction2() \n" + 
				"		42\n" + 
				"		\n" + 
				"	Integer testFunction3() \n" + 
				"		42\n" + 
				"		\n" + 
				"}";
		doTestUpdateAtOffset(model, 51, 6, " ", "InferredModelRemoved.xtend");
	}
	
	protected void validateWithoutException(XtextResource resource) {
		ResourceValidatorImpl validator = new ResourceValidatorImpl();
		assertNotSame(validator, resource.getResourceServiceProvider().getResourceValidator());
		getInjector().injectMembers(validator);
		validator.setDiagnosticConverter(new IDiagnosticConverter() {
			public void convertValidatorDiagnostic(org.eclipse.emf.common.util.Diagnostic diagnostic, IAcceptor<Issue> acceptor) {
				if (diagnostic instanceof BasicDiagnostic) {
					List<?> data = diagnostic.getData();
					if (!data.isEmpty() && data.get(0) instanceof Throwable) {
						Throwable t = (Throwable) data.get(0);
						// the framework catches runtime exception
						// and AssertionError does not take a throwable as argument
						throw new Error(t);
					}
					if (EObjectValidator.DIAGNOSTIC_SOURCE.equals(diagnostic.getSource()) && diagnostic.getCode() == EObjectValidator.EOBJECT__EVERY_REFERENCE_IS_CONTAINED) {
						throw new Error(new RuntimeException("Dangling reference found."));
					}
				}
			}
			
			public void convertResourceDiagnostic(Diagnostic diagnostic, Severity severity, IAcceptor<Issue> acceptor) {
				if (diagnostic instanceof ExceptionDiagnostic) {
					Exception e = ((ExceptionDiagnostic) diagnostic).getException();
					// the framework catches runtime exception
					// and AssertionError does not take a throwable as argument
					throw new Error(new RuntimeException(e));
				}
			}
		});
		validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
	}
	
	protected XtextResource doTestUpdateAtEnd(String model, char character, String fileName) throws IOException {
		return doTestUpdateAtOffset(model, model.length(), 0, String.valueOf(character), fileName);
	}

	protected XtextResource doTestUpdateAtOffset(String model, int offset, int length, String newText, String fileName) throws IOException {
		XtextResource resource = createResource(model, fileName);
		compareWithNewResource(resource, model, offset, length, newText, fileName);
		return resource;
	}

	protected XtextResource createResource(String model, String fileName) throws IOException {
		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		XtextResource resource = (XtextResource) resourceSet.createResource(URI.createURI(fileName));
		resource.load(new StringInputStream(model), null);
		return resource;
	}

	protected void compareWithNewResource(XtextResource resource, String model, int offset, int length, String newText,
			String fileName) throws IOException {
		resource.update(offset, length, newText);
		XtextResourceSet secondResourceSet = get(XtextResourceSet.class);
		XtextResource newResource = (XtextResource) secondResourceSet.createResource(URI.createURI(fileName));
		String newModel = new StringBuilder(model).replace(offset, offset + length, newText).toString();
		assertEquals(newModel, resource.getParseResult().getRootNode().getText());
		newResource.load(new StringInputStream(newModel), null);
		assertEquals(newResource.getContents().size(), resource.getContents().size());
		for(int i = 0; i < resource.getContents().size(); i++) {
			if (!EcoreUtil.equals(resource.getContents().get(i), newResource.getContents().get(i))) {
				for(int j = 0; j < resource.getContents().size(); j++) {
					String actualContent = EmfFormatter.objToStr(resource.getContents().get(j));
					assertEquals(actualContent, EmfFormatter.objToStr(newResource.getContents().get(j)), actualContent);
				}
			}
		}
		assertEqualNodes(newResource, resource);
	}
	
	protected void assertEqualNodes(XtextResource expected, XtextResource actual) throws IOException {
		ICompositeNode expectedRoot = expected.getParseResult().getRootNode();
		invariantChecker.checkInvariant(expectedRoot);
		ICompositeNode actualRoot = actual.getParseResult().getRootNode();
		invariantChecker.checkInvariant(actualRoot);
		Iterator<INode> expectedIter = expectedRoot.getAsTreeIterable().iterator();
		Iterator<INode> actualIter = actualRoot.getAsTreeIterable().iterator();
		while(expectedIter.hasNext()) {
			assertTrue(actualIter.hasNext());
			assertEqualNodes(expectedIter.next(), actualIter.next());
		}
		assertFalse(actualIter.hasNext());
	}

	protected void assertEqualNodes(INode expected, INode actual) {
		assertEquals("class", expected.getClass(), actual.getClass());
		assertEquals("text", expected.getText(), actual.getText());
		assertEquals("total offset", expected.getTotalOffset(), actual.getTotalOffset());
		assertEquals("total length", expected.getTotalLength(), actual.getTotalLength());
		assertEquals("grammar element", expected.getGrammarElement(), actual.getGrammarElement());
		assertEquals("direct semantic element", expected.hasDirectSemanticElement(), actual.hasDirectSemanticElement());
//		assertTrue(EcoreUtil.equals(expected.getSemanticElement(), actual.getSemanticElement()));
		assertEquals("syntax error message", expected.getSyntaxErrorMessage(), actual.getSyntaxErrorMessage());
		if (expected instanceof ICompositeNode) {
			assertEquals("lookAhead", ((ICompositeNode) expected).getLookAhead(), ((ICompositeNode) actual).getLookAhead());
		}
	}
}
