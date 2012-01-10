/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.tests.validation;

import static org.eclipse.xtext.xbase.validation.IssueCodes.*;
import static org.eclipse.xtext.xtend2.validation.IssueCodes.*;
import static org.eclipse.xtext.xtend2.xtend2.Xtend2Package.Literals.*;

import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xtend2.tests.AbstractXtend2TestCase;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class OverrideValidationTest extends AbstractXtend2TestCase {

	@Inject
	private ValidationTestHelper helper;

	//TODO override declarations in interfaces

	@Test public void testDuplicateMethod_0() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def bar(int x) {true} def bar(int x) {false} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DUPLICATE_METHOD, "duplicate");
	}

	@Test public void testDuplicateMethod_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def bar(String x) {true} def bar(int x) {false} }");
		helper.assertNoError(xtendClass, DUPLICATE_METHOD);
	}

	@Test public void testDuplicateMethod_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def bar(String x) {true} def bar(String x, int x) {false} }");
		helper.assertNoError(xtendClass, DUPLICATE_METHOD);
	}

	@Test public void testDuplicateMethod_3() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def bar(java.util.List<String> x) {true} def bar(java.util.List<Integer> x) {false} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure", "List)",
				"List<String");
		helper.assertError(xtendClass.getMembers().get(1), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure", "List)",
				"List<Integer");
	}

	@Test public void testDuplicateMethod_4() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def <T> bar(T t) { '' } def <T> bar(T t) { 1 } }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DUPLICATE_METHOD, "duplicate", "bar(T)");
		helper.assertError(xtendClass.getMembers().get(1), XTEND_FUNCTION, DUPLICATE_METHOD, "duplicate", "bar(T)");
	}

	@Test public void testDuplicateMethod_5() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def <T> bar(T t) { '' } def <V> bar(V v) { 1 } }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure", "Object)", "T");
		helper.assertError(xtendClass.getMembers().get(1), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure", "Object)", "V");
	}

	@Test public void testDuplicateMethod_6() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def <T> bar(CharSequence seq) { '' } def <V extends CharSequence> bar(V v) { 1 } }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure", "CharSequence)");
		helper.assertError(xtendClass.getMembers().get(1), XTEND_FUNCTION, DUPLICATE_METHOD, "erasure",
				"CharSequence)", "bar(V)");
	}

	@Test public void testDuplicateMethod_7() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def <T extends CharSequence> bar(T t) { '' } def <V extends String> bar(V v) { 1 } }");
		helper.assertNoErrors(xtendClass);
	}

	@Test public void testObsoleteOverride_0() throws Exception {
		XtendClass xtendClass = clazz("class Foo { override bar() {true} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OBSOLETE_OVERRIDE);
	}

	@Test public void testObsoleteOverride_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override bar() {true} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OBSOLETE_OVERRIDE);
	}

	@Test public void testObsoleteOverride_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override privateMethod() {true}}");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OBSOLETE_OVERRIDE);
	}

	@Test public void testMissingOverride_0() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def string() {null} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, MISSING_OVERRIDE);
	}

	@Test public void testMissingOverride_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def string(int i) { null} }");
		helper.assertNoError(xtendClass.getMembers().get(0), MISSING_OVERRIDE);
	}

	@Test public void testMissingOverride_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def string(String s) {null} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, MISSING_OVERRIDE);
	}

	@Test public void testMissingOverride_3() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def object(String s) {null} }");
		helper.assertNoError(xtendClass.getMembers().get(0), MISSING_OVERRIDE);
	}

	@Test public void testMissingOverride_4() throws Exception {
		XtendClass xtendClass = clazz("class Foo implements test.SomeInterface { def foo() { true } }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, MISSING_OVERRIDE);
	}

	@Test public void testMissingOverride_5() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def boolean equals(Object x) { return true } }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, MISSING_OVERRIDE);
	}

	@Test public void testIncompatibleReturnType_0() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override Boolean string() {true} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testIncompatibleReturnType_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override String object() {''} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testIncompatibleReturnType_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo implements test.SomeInterface { override void foo() {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testIncompatibleGenericReturnType_0() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override java.util.ArrayList<String> returnsListString() {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testIncompatibleGenericReturnType_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override java.util.List<Object> returnsListString() {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testIncompatibleGenericReturnType_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override java.util.ArrayList<String> returnsListExtendsObject() {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testOverrideVoidFunction() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override voidFunction() {''} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testOverrideVoidFunction_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { override String voidFunction() {''} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testOverrideWithTypeParameter() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.GenericClass { override java.util.List<String> foo() {newArrayList} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testOverrideWithTypeParameter_1() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.GenericClass<String> { override java.util.List<String> foo() {newArrayList} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testOverrideWithTypeParameter_2() throws Exception {
		XtendClass xtendClass = clazz("class Foo<S> extends test.GenericClass<S> { override java.util.List<S> foo() {null} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_RETURN_TYPE);
	}

	@Test public void testClassMustBeAbstract_01() throws Exception {
		XtendClass xtendClass = clazz("class Foo<S> implements Comparable<S> { }");
		helper.assertError(xtendClass, XTEND_CLASS, CLASS_MUST_BE_ABSTRACT, "abstract", "not", "implement",
				"compareTo(S)");
	}

	@Test public void testClassMustBeAbstract_02() throws Exception {
		XtendClass xtendClass = clazz("class Foo<S> implements Comparable { }");
		helper.assertError(xtendClass, XTEND_CLASS, CLASS_MUST_BE_ABSTRACT, "abstract", "not", "implement",
				"compareTo(Object)");
	}

	@Test public void testClassMustBeAbstract_03() throws Exception {
		XtendClass xtendClass = clazz("class Foo implements Comparable<String> { }");
		helper.assertError(xtendClass, XTEND_CLASS, CLASS_MUST_BE_ABSTRACT, "abstract", "not", "implement",
				"compareTo(String)");
	}

	@Test public void testClassMustBeAbstract_04() throws Exception {
		XtendClass xtendClass = clazz("class Foo implements Comparable { }");
		helper.assertError(xtendClass, XTEND_CLASS, CLASS_MUST_BE_ABSTRACT, "abstract", "not", "implement",
				"compareTo(Object)");
	}

//		@Test public void testClassMustBeAbstract_05() throws Exception {
//			XtendClass xtendClass = clazz("class MyList<T> extends java.util.ArrayList<T> { }");
//			helper.assertNoErrors(xtendClass);
//		}
//		
//		@Test public void testClassMustBeAbstract_06() throws Exception {
//			XtendClass xtendClass = clazz("class StringList extends java.util.ArrayList<StringList> { }");
//			helper.assertNoErrors(xtendClass);
//		}

	@Test public void testOverrideFinalClass() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends String { }");
		helper.assertError(xtendClass, XTEND_CLASS, OVERRIDDEN_FINAL, "override", "final");
	}

	@Test public void testOverrideFinalMethod() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ClassWithFinalMembers { def foo() {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OVERRIDDEN_FINAL, "override", "final");
	}

	@Test public void testOverrideSameVisibility() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.Visibilities { override publicMethod() {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), OVERRIDE_REDUCES_VISIBILITY);
	}

	@Test public void testOverrideReveals() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.Visibilities { override public protectedMethod() {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), OVERRIDE_REDUCES_VISIBILITY);
	}

	@Test public void testOverrideHides() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.Visibilities { override protected publicMethod() {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OVERRIDE_REDUCES_VISIBILITY, "reduce",
				"visibility");
	}

	@Test public void testDispatchHides() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.Visibilities { def protected dispatch publicMethod() {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, OVERRIDE_REDUCES_VISIBILITY, "reduce",
				"visibility");
	}

	@Test public void testIncompatibleThrowsClause() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override ioException() throws Exception {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_THROWS_CLAUSE,
				"Exception", "not", "compatible", "throws", "clause");
	}
	
	@Test public void testIncompatibleThrowsClause_01() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override runtimeException() throws Exception {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_THROWS_CLAUSE,
				"Exception", "not", "compatible", "throws", "clause");
	}
	
	@Test public void testIncompatibleThrowsClause_02() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override nullPointerException() throws Exception {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_THROWS_CLAUSE,
				"Exception", "not", "compatible", "throws", "clause");
	}
	
	@Test public void testIncompatibleThrowsClause_03() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override noException() throws Exception {} }");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, INCOMPATIBLE_THROWS_CLAUSE,
				"Exception", "not", "compatible", "throws", "clause");
	}
	
	@Test public void testCompatibleThrowsClause() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override ioException() throws java.io.FileNotFoundException {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_01() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override runtimeException() throws NullPointerException {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_02() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override nullPointerException() throws RuntimeException {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_03() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override noException() throws RuntimeException {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_04() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override throwable() throws RuntimeException {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_05() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override throwable() throws Exception {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_06() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override throwable() throws Throwable {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

	@Test public void testCompatibleThrowsClause_07() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.ExceptionThrowing { override ioException() {} }");
		helper.assertNoError(xtendClass.getMembers().get(0), INCOMPATIBLE_THROWS_CLAUSE);
	}

}
