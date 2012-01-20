/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.tests.validation;

import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.*;
import static org.eclipse.xtext.xbase.XbasePackage.Literals.*;
import static org.eclipse.xtext.xbase.validation.IssueCodes.*;
import static org.eclipse.xtext.xtend2.validation.IssueCodes.*;
import static org.eclipse.xtext.xtend2.xtend2.Xtend2Package.Literals.*;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationsPackage;
import org.eclipse.xtext.xtend2.tests.AbstractXtend2TestCase;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.eclipse.xtext.xtend2.xtend2.XtendConstructor;
import org.eclipse.xtext.xtend2.xtend2.XtendFile;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;
import org.eclipse.xtext.xtend2.xtend2.XtendMember;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class Xtend2ValidationTest extends AbstractXtend2TestCase {

	@Inject
	private ValidationTestHelper helper;
	
	@Test public void testFieldInitializerType_01() throws Exception {
		XtendClass clazz = clazz("class Z { String s = 1 }");
		helper.assertError(clazz, XINT_LITERAL, INCOMPATIBLE_RETURN_TYPE);
	}
	
	@Test public void testFieldInitializerType_02() throws Exception {
		XtendClass clazz = clazz("class Z { String s = 1.toString }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testFieldInitializerType_03() throws Exception {
		XtendClass clazz = clazz("class Z { String s = return 1 }");
		helper.assertError(clazz, XINT_LITERAL, INCOMPATIBLE_TYPES);
	}
	
	@Test public void testFieldInitializerType_04() throws Exception {
		XtendClass clazz = clazz("class Z { String s = return '' }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_01() throws Exception {
		XtendClass clazz = clazz("class Z { String s = s }");
		helper.assertError(clazz, XFEATURE_CALL, ILLEGAL_FORWARD_REFERENCE);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_02() throws Exception {
		XtendClass clazz = clazz("class Z { String s1 = newArrayList(s2 + '').toString String s2 = '' }");
		helper.assertError(clazz, XFEATURE_CALL, ILLEGAL_FORWARD_REFERENCE);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_03() throws Exception {
		XtendClass clazz = clazz("class Z { static String s = s }");
		helper.assertError(clazz, XFEATURE_CALL, ILLEGAL_FORWARD_REFERENCE);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_04() throws Exception {
		XtendClass clazz = clazz("class Z { static String s1 = s2 static String s2 = '' }");
		helper.assertError(clazz, XFEATURE_CALL, ILLEGAL_FORWARD_REFERENCE);
	}

	@Test public void testForwardReferenceInFieldInitializer_05() throws Exception {
		XtendClass clazz = clazz("class Z { String s = '' }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_06() throws Exception {
		XtendClass clazz = clazz("class Z { String s1 = '' String s2 = s1 }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_07() throws Exception {
		XtendClass clazz = clazz("class Z { static String s = '' }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testForwardReferenceInFieldInitializer_08() throws Exception {
		XtendClass clazz = clazz("class Z { static String s1 = '' static String s2 = s1 }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testCircularConstructor_01() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this() }}");
		helper.assertError(clazz, XFEATURE_CALL, CIRCULAR_CONSTRUCTOR_INVOCATION);
	}
	
	@Test public void testCircularConstructor_02() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(1) } new(int a) { this() } }");
		helper.assertError(clazz.getMembers().get(0), XFEATURE_CALL, CIRCULAR_CONSTRUCTOR_INVOCATION);
		helper.assertError(clazz.getMembers().get(1), XFEATURE_CALL, CIRCULAR_CONSTRUCTOR_INVOCATION);
	}
	
	@Test public void testCircularConstructor_03() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(1) } new(int a) { super() } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorCallIsFirst_01() throws Exception {
		XtendClass clazz = clazz("class Z { new() { super() } new(int a) { this() } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorCallIsFirst_02() throws Exception {
		XtendClass clazz = clazz("class Z { new() { { this() } }}");
		helper.assertError(clazz, XFEATURE_CALL, INVALID_CONSTRUCTOR_INVOCATION);
	}
	
	@Test public void testConstructorCallIsFirst_03() throws Exception {
		XtendClass clazz = clazz("class Z { new() { if (true) super() } }");
		helper.assertError(clazz, XFEATURE_CALL, INVALID_CONSTRUCTOR_INVOCATION);
	}
	
	@Test public void testConstructorArgumentIsValid_01() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(1) } new(int i) {} }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_02() throws Exception {
		XtendClass clazz = clazz("class Z { static int j new() { this(j) } new(int i) {} }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_03() throws Exception {
		XtendClass clazz = clazz("class Z { int j new() { this(j) } new(int i) {} }");
		helper.assertError(clazz.getMembers().get(1), XFEATURE_CALL, INVALID_CONSTRUCTOR_ARGUMENT);
	}
	
	@Test public void testConstructorArgumentIsValid_04() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z()) } new(Object o) {} def z() { null } }");
		helper.assertError(clazz.getMembers().get(0), XFEATURE_CALL, INVALID_CONSTRUCTOR_ARGUMENT);
	}
	
	@Test public void testConstructorArgumentIsValid_05() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(null as Object) } new(Object o) {} }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_06() throws Exception {
		XtendClass clazz = clazz("class Z { new(Object o) { this(o as String) } new(String o) {} }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_07() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z()) } new(Object o) {} def static z() { null } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_08() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z() as String) } new(Object o) {} def static z() { null } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_09() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z() as String) } new(Object o) {} def z() { null } }");
		helper.assertError(clazz.getMembers().get(0), XFEATURE_CALL, INVALID_CONSTRUCTOR_ARGUMENT);
	}
	
	@Test public void testConstructorArgumentIsValid_10() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z().toString + '') } new(Object o) {} def static z() { null } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testConstructorArgumentIsValid_11() throws Exception {
		XtendClass clazz = clazz("class Z { new() { this(z().toString + '') } new(Object o) {} def z() { null } }");
		helper.assertError(clazz.getMembers().get(0), XFEATURE_CALL, INVALID_CONSTRUCTOR_ARGUMENT);
	}
	
	@Test public void testConstructorDuplicate() throws Exception {
		XtendClass clazz = clazz("class K { new(Object o) {} new(Object o) {} }");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, DUPLICATE_METHOD);
		helper.assertError(clazz, XTEND_CONSTRUCTOR, DUPLICATE_METHOD);
	}
	
	@Test public void testConstructorDuplicateErasure() throws Exception {
		XtendClass clazz = clazz("class K { new(List<Object> o) {} new(List<String> o) {} }");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, DUPLICATE_METHOD);
		helper.assertError(clazz, XTEND_CONSTRUCTOR, DUPLICATE_METHOD);
	}
	
	@Test public void testConstructorDoesNotSupportTypeArguments() throws Exception {
		XtendClass clazz = clazz("class K { new<T>() {} }");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, CONSTRUCTOR_TYPE_PARAMS_NOT_SUPPORTED);
	}

	@Test public void testMissingConstructor() throws Exception {
		XtendClass clazz = clazz("class K extends test.NoDefaultConstructor {}");
		helper.assertError(clazz, XTEND_CLASS, MISSING_CONSTRUCTOR);
	}
	
	@Test public void testMissingSuperConstructorCall() throws Exception {
		XtendClass clazz = clazz("class K extends test.NoDefaultConstructor { new() {} }");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, MUST_INVOKE_SUPER_CONSTRUCTOR);
	}
	
	@Test public void testSuperConstructorCall() throws Exception {
		XtendClass clazz = clazz("class K extends test.NoDefaultConstructor { new() { super('') } }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testReturnStatement() throws Exception {
		XtendClass clazz = clazz("class Z { def void foo() { return 'holla' }}");
		helper.assertError(clazz, XRETURN_EXPRESSION, INVALID_RETURN);
	}
	
	@Test public void testReturnStatement1() throws Exception {
		XtendClass clazz = clazz("class Z { def Object foo() { return }}");
		helper.assertError(clazz, XRETURN_EXPRESSION, INVALID_RETURN);
	}
	
	@Test public void testBug_357230() throws Exception {
		XtendClass clazz = clazz(
				"package x class Z {" +
				"  def dispatch _foo(Object x, boolean b) {}\n" +
				"  def dispatch _foo(String x, boolean b) {}\n" +
				"}\n");
		helper.assertError(clazz, XTEND_FUNCTION, DISPATCH_FUNC_NAME_STARTS_WITH_UNDERSCORE);
	}
	
	@Test public void testAnnotationTarget_00() throws Exception {
		XtendClass clazz = clazz("@testdata.Annotation2('foo') class X { }");
		helper.assertError(clazz, XAnnotationsPackage.Literals.XANNOTATION, ANNOTATION_WRONG_TARGET, "@Annotation2");
	}
	
	@Test public void testAnnotationTarget_01() throws Exception {
		XtendClass clazz = clazz("class X { @testdata.Annotation2('foo')  String foo }");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testAnnotationTarget_02() throws Exception {
		XtendClass clazz = clazz("class X { @testdata.Annotation2('foo') def String foo() }");
		helper.assertError(clazz, XAnnotationsPackage.Literals.XANNOTATION, ANNOTATION_WRONG_TARGET);
	}
	
	@Test public void testAnnotationTarget_03() throws Exception {
		XtendClass clazz = clazz("class X {  def String foo(@testdata.Annotation2('foo') String bar) }");
		helper.assertError(clazz, XAnnotationsPackage.Literals.XANNOTATION, ANNOTATION_WRONG_TARGET);
	}
	
	@Test public void testShadowingVariableNames_00() throws Exception {
		XtendClass clazz = clazz("class X { def foo() { val this = 'foo' } }");
		helper.assertError(clazz, XVARIABLE_DECLARATION, VARIABLE_NAME_SHADOWING);
	}
	
	@Test public void testShadowingVariableNames_01() throws Exception {
		XtendClass clazz = clazz("class X { def foo() { val ^super = 'foo' } }");
		helper.assertError(clazz, XVARIABLE_DECLARATION, VARIABLE_NAME_SHADOWING);
	}
	
	@Test public void testShadowingVariableNames_03() throws Exception {
		XtendClass clazz = clazz("class X { def foo(String this) { } }");
		helper.assertError(clazz, XTEND_PARAMETER, VARIABLE_NAME_SHADOWING);
	}
	
	@Test public void testShadowingVariableNames_04() throws Exception {
		XtendClass clazz = clazz("class X { def foo(String x) ''' �val x = 'foo'� �x� ''' }");
		helper.assertError(clazz, XVARIABLE_DECLARATION, VARIABLE_NAME_SHADOWING);
	}
	
	@Test public void testShadowingVariableNames_05() throws Exception {
		XtendClass clazz = clazz("class X { def foo() ''' �val x = 'foo'� �val x = 'bar'� ''' }");
		helper.assertError(clazz, XVARIABLE_DECLARATION, VARIABLE_NAME_SHADOWING);
	}
	
	@Test public void testVoidInDependency() throws Exception {
		XtendClass clazz = clazz("class X { @Inject void v }");
		helper.assertError(clazz, TypesPackage.Literals.JVM_TYPE_REFERENCE, INVALID_USE_OF_TYPE);
	}
	
	@Test public void testVoidInReturn() throws Exception {
		XtendFunction function = function("def void foo() { }");
		helper.assertNoError(function, INVALID_USE_OF_TYPE);
	}
	
	@Test public void testParameterTypeMayNotBeVoid() throws Exception {
		XtendFunction function = function("def void foo(void myParam) { }");
		helper.assertError(function, TypesPackage.Literals.JVM_TYPE_REFERENCE, INVALID_USE_OF_TYPE);
	}
	
	@Test public void testCreateMayNotReturnVoid() throws Exception {
		XtendFunction function = function("def void create result: new Object() newObject() { }");
		helper.assertError(function, TypesPackage.Literals.JVM_TYPE_REFERENCE, INVALID_USE_OF_TYPE, "void", "create", "newObject");
	}
	
	@Test public void testCreateMayNotBeStatic() throws Exception {
		XtendFunction function = function("def static create new Object() newObject() { }");
		helper.assertError(function, XTEND_FUNCTION, INVALID_USE_OF_STATIC, "not", "static");
	}
	
	@Test public void testCreateExpressionMayNotReturnVoid_01() throws Exception {
		XtendFunction function = function("def create result: while(true){} illegal() { }");
		helper.assertError(function, XTEND_FUNCTION, INVALID_USE_OF_TYPE, "void", "create", "method", "illegal");
	}
	
	@Test public void testCreateExpressionMayNotReturnVoid_02() throws Exception {
		XtendFunction function = function("def String create result: while(true){} illegal() { }");
		helper.assertError(function, XWHILE_EXPRESSION, INCOMPATIBLE_RETURN_TYPE, "implicit", "return", "type", "void", "String");
	}

	@Test public void testCreateExpressionMayNotReturnVoid_03() throws Exception {
		XtendFunction function = function("override create result: while(true){} toString() { }");
		helper.assertError(function, XWHILE_EXPRESSION, INCOMPATIBLE_RETURN_TYPE, "implicit", "return", "type", "void", "String");
	}
	
	@Test public void testNoReturnInCreateFunctions() throws Exception {
		XtendFunction function = function("def create result: if (true) return 'foo' else 'bar' foo() { }");
		helper.assertError(function, XRETURN_EXPRESSION, INVALID_EARLY_EXIT);
	}
	
	@Test public void testNoReturnInCreateFunctions_00() throws Exception {
		XtendFunction function = function("def create result: [|if (true) return 'foo' else 'bar'] foo() { }");
		helper.assertNoErrors(function);
	}
	
	@Test public void testNoReturnInCreateFunctions_01() throws Exception {
		XtendFunction function = function("def create result:'foo' foo() { return 'bar' }");
		helper.assertError(function, XSTRING_LITERAL, INCOMPATIBLE_TYPES);
	}
	
	@Test public void testNoReturnInCreateFunctions_02() throws Exception {
		XtendFunction function = function("def create result:'foo' foo() { return }");
		helper.assertNoErrors(function);
	}
	
	@Test public void testNoReturnInCreateFunctions_03() throws Exception {
		XtendFunction function = function("def create result:'foo' foo() { [|return 'foo'].apply() }");
		helper.assertNoErrors(function);
	}
	
	@Test public void testNoReturnInCreateFunctions_04() throws Exception {
		XtendFunction function = function("def create result:'foo' foo() { if (true) 'foo'+'bar' else return 'baz' }");
		helper.assertError(function, XSTRING_LITERAL, INCOMPATIBLE_TYPES);
	}

	@Test public void testReturnTypeCompatibility_00() throws Exception {
		XtendFunction function = function("def void foo(int bar) { }");
		helper.assertNoErrors(function);
	}

	@Test public void testReturnTypeCompatibility_01() throws Exception {
		XtendFunction function = function("def String foo(int bar) { return 42 }");
		helper.assertError(function, XINT_LITERAL, INCOMPATIBLE_TYPES, "String", "int");
	}

	@Test public void testReturnTypeCompatibility_02() throws Exception {
		XtendFunction function = function("def Object foo(int bar) { return 42 }");
		helper.assertNoErrors(function);
	}

	@Test public void testReturnTypeCompatibility_03() throws Exception {
		XtendFunction function = function(
				  "def String foo(int bar) { " 
				+ " if (true) {"
				+ "  return if (false) 42 else new Object()" 
				+ " }" 
				+ "}");
		helper.assertError(function, XIF_EXPRESSION, INCOMPATIBLE_TYPES, "String", "Object");
	}

	@Test public void testReturnTypeCompatibility_04() throws Exception {
		XtendClass clazz = clazz("class Foo implements test.SomeInterface { def foo() { 1 } }");
		helper.assertError(clazz.getMembers().get(0), XTEND_FUNCTION, MISSING_OVERRIDE);
	}

	@Test public void testReturnTypeCompatibility_05() throws Exception {
		XtendClass clazz = clazz("class Foo implements test.SomeInterface { override foo() { true } }");
		helper.assertNoErrors(clazz.getMembers().get(0));
	}
	
	@Test public void testReturnTypeCompatibility_06() throws Exception {
		XtendClass clazz = clazz("class Foo { def dispatch void a(String x) {} def dispatch a(Object x) {return null} }");
		helper.assertError(clazz.getMembers().get(1), XTEND_FUNCTION, INCOMPATIBLE_RETURN_TYPE);
	}
	
	@Test public void testReturnTypeCompatibility_07() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def void a() {" +
				"    val closure = [Integer i| return i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testReturnTypeCompatibility_08() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def void a() {" +
				"    val closure = [Integer i| i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testReturnTypeCompatibility_09() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def void a() {" +
				"    val (Integer)=>Integer closure = [Integer i| return i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}

	@Test public void testReturnTypeCompatibility_10() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def a() {" +
				"    val closure = [Integer i| return i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testReturnTypeCompatibility_11() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def a() {" +
				"    val closure = [Integer i| i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testReturnTypeCompatibility_12() throws Exception {
		XtendClass clazz = clazz(
				"class Foo { " +
				"  def a() {" +
				"    val (Integer)=>Integer closure = [Integer i| return i]\n" + 
				"    for (x : 1..100) closure.apply(x)" +
				"  }" +
				"}");
		helper.assertNoErrors(clazz);
	}
	
	@Test public void testThrowsClauseCompatibility_00() throws Exception {
		XtendClass clazz = clazz(
				"class Foo {" +
				"  def foo() {" +
				"    throw new Exception()" +
				"  }" +
				"}");
		helper.assertNoError(clazz, UNHANDLED_EXCEPTION);
//		helper.assertError(clazz, XBLOCK_EXPRESSION, UNHANDLED_EXCEPTION, "unhandled", "exception");
	}
	
	@Test public void testThrowsClauseCompatibility_01() throws Exception {
		XtendClass clazz = clazz(
				"class Foo {" +
				"  def foo() {" +
				"    throw new RuntimeException()" +
				"  }" +
				"}");
		helper.assertNoError(clazz, UNHANDLED_EXCEPTION);
	}
	
	@Test public void testThrowsClauseCompatibility_02() throws Exception {
		XtendClass clazz = clazz(
				"class Foo {" +
				"  def foo() throws RuntimeException {" +
				"    throw new Exception()" +
				"  }" +
				"}");
		helper.assertNoError(clazz, UNHANDLED_EXCEPTION);
//		helper.assertError(clazz, XBLOCK_EXPRESSION, UNHANDLED_EXCEPTION, "unhandled", "exception");
	}
	
	@Test public void testThrowsClauseCompatibility_03() throws Exception {
		XtendClass clazz = clazz(
				"class Foo {" +
				"  def foo() throws Exception {" +
				"    throw new NoSuchFieldException()" +
				"  }" +
				"}");
		helper.assertNoError(clazz, UNHANDLED_EXCEPTION);
	}
	
	@Test public void testThrowsClauseCompatibility_04() throws Exception {
		XtendClass clazz = clazz(
				"class Foo {" +
				"  def foo() {" +
				"    try {" +
				"      throw new NoSuchFieldException()" +
				"    } catch(NoSuchFieldException e) {" +
				"    }" +
				"  }" +
				"}");
		helper.assertNoError(clazz, UNHANDLED_EXCEPTION);
	}
	
	@Test public void testAssignmentToFunctionParameter() throws Exception {
		XtendFunction function = function("def void foo(int bar) { bar = 7 }");
		helper.assertError(function, XASSIGNMENT, ASSIGNMENT_TO_FINAL, "Assignment", "final",
				"parameter");
	}

	@Test public void testClassExtendsInterface() throws Exception {
		XtendClass clazz = clazz("class Foo extends Cloneable {}");
		helper.assertError(clazz, XTEND_CLASS, CLASS_EXPECTED, "Superclass");
	}

	@Test public void testClassImplementsClass() throws Exception {
		XtendClass clazz = clazz("class Foo implements Object {}");
		helper.assertError(clazz, XTEND_CLASS, INTERFACE_EXPECTED, "Implemented", "interface");
	}
	
	@Test public void testClassExtendsItself() throws Exception {
		XtendClass clazz = clazz("class Foo extends Foo {}");
		helper.assertError(clazz, XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
	}
	
	@Test public void testInheritanceCycle() throws Exception {
		Iterator<XtendFile> iter = files(false, 
				 "package test class Foo extends Bar {}"
				,"package test class Bar extends Baz {}"
				,"package test class Baz extends Foo {}").iterator();
		waitForAutoBuild();
		helper.assertError(iter.next(), XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
		helper.assertError(iter.next(), XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
		helper.assertError(iter.next(), XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
	}
	
	@Test public void testInheritanceCycle_1() throws Exception {
		Iterator<XtendFile> iter = files(false, 
				 "package test class Foo extends Bar {}"
				,"package test class Bar extends Foo {}").iterator();
		waitForAutoBuild();
		helper.assertError(iter.next(), XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
		helper.assertError(iter.next(), XTEND_CLASS, CYCLIC_INHERITANCE, "hierarchy", "cycles");
	}
	
	@Test public void testIgnoreInterfacesInCycleDetection() throws Exception {
		files(true, 
				 "package test class Foo extends Bar {}"
				,"package test class Bar extends Baz implements java.io.Serializable {}"
				,"package test class Baz implements java.io.Serializable {}");
	}

	@Test public void testInvalidFieldName_00() throws Exception {
		XtendClass clazz = clazz("class Foo { int this }");
		helper.assertError(clazz.getMembers().get(0), XTEND_FIELD, INVALID_MEMBER_NAME, "'this'", "'it'", "names");
	}
	
	@Test public void testInvalidFieldName_01() throws Exception {
		XtendClass clazz = clazz("class Foo { int it }");
		helper.assertError(clazz.getMembers().get(0), XTEND_FIELD, INVALID_MEMBER_NAME, "'this'", "'it'", "names");
	}
	
	@Test public void testInvalidMethodName_00() throws Exception {
		XtendClass clazz = clazz("class Foo { def void this() {} }");
		helper.assertError(clazz.getMembers().get(0), XTEND_FUNCTION, INVALID_MEMBER_NAME, "'this'", "'it'", "names");
	}
	
	@Test public void testInvalidMethodName_01() throws Exception {
		XtendClass clazz = clazz("class Foo { def void it() {} }");
		helper.assertError(clazz.getMembers().get(0), XTEND_FUNCTION, INVALID_MEMBER_NAME, "'this'", "'it'", "names");
	}
	
	@Test public void testDuplicateFieldName() throws Exception {
		XtendClass clazz = clazz("class Foo { int foo String foo double foo }");
		for(XtendMember member: clazz.getMembers())
			helper.assertError(member, XTEND_FIELD, DUPLICATE_FIELD, "foo", "duplicate");
	}
	
	@Test public void testDuplicateAnonymousExtension() throws Exception {
		XtendClass clazz = clazz("import com.google.inject.Inject class Foo { @Inject extension String @Inject extension String }");
		for(XtendMember member: clazz.getMembers())
			helper.assertError(member, XTEND_FIELD, DUPLICATE_FIELD, "duplicate", "same", "type");
	}
	
	@Test public void testCaseFunctionNoParameters() throws Exception {
		XtendFunction function = function("def dispatch foo() { null }");
		helper.assertError(function, XTEND_FUNCTION, DISPATCH_FUNC_WITHOUT_PARAMS);
	}

	@Test public void testCaseFunctionWithTypeParams() throws Exception {
		XtendFunction function = function("def dispatch <T> foo(T s) { null }");
		helper.assertError(function, XTEND_FUNCTION, DISPATCH_FUNC_WITH_TYPE_PARAMS);
	}

	@Test public void testSingleCaseFunction() throws Exception {
		XtendFunction function = function("def dispatch foo(String s) { null }");
		helper.assertWarning(function, XTEND_FUNCTION, SINGLE_DISPATCH_FUNCTION);
	}

	@Test public void testDuplicateCaseFunction() throws Exception {
		XtendFunction function = function("def dispatch foo(Integer s) { null } def dispatch foo(int s) { null }");
		helper.assertError(function, XTEND_FUNCTION, DUPLICATE_METHOD, "dispatch");
	}

	@Test public void testInaccessibleMethod() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def foo() { privateMethod() }}");
		helper.assertError(((XBlockExpression) ((XtendFunction) xtendClass.getMembers().get(0)).getExpression())
				.getExpressions().get(0), XABSTRACT_FEATURE_CALL, FEATURE_NOT_VISIBLE, "Feature",
				"not", "visible");
	}
	
	@Test public void testInaccessibleStaticMethod() throws Exception {
		XtendClass xtendClass = clazz("class Foo extends test.SuperClass { def foo() { privateStaticMethod() }}");
		helper.assertError(((XBlockExpression) ((XtendFunction) xtendClass.getMembers().get(0)).getExpression())
				.getExpressions().get(0), XABSTRACT_FEATURE_CALL, FEATURE_NOT_VISIBLE, "Feature",
				"not", "visible");
	}

	//TODO fails since Object is explicitly extended.
//	@Test public void testInaccessibleMethod2() throws Exception {
//		XtendClass xtendClass = clazz("class Foo { def foo() { val o = new Object() o.clone() }}");
//		helper.assertError(((XBlockExpression) ((XtendFunction) xtendClass.getMembers().get(0)).getExpression())
//				.getExpressions().get(1), XABSTRACT_FEATURE_CALL, FEATURE_NOT_VISIBLE, "Feature",
//				"not", "visible");
//	}

	@Test public void testDuplicateParameter() throws Exception {
		XtendFunction function = function("def foo(int x, int x) {null}");
		helper.assertError(function, XTEND_FUNCTION, DUPLICATE_PARAMETER_NAME, "duplicate", "x");
	}
	
	@Test public void testDuplicateConstructorParameter() throws Exception {
		XtendConstructor constructor = constructor("new(int x, int x) {null}");
		helper.assertError(constructor, XTEND_CONSTRUCTOR, DUPLICATE_PARAMETER_NAME, "duplicate", "x");
	}
	
	@Test public void testDuplicateParameter_CreateExtension_01() throws Exception {
		XtendFunction function = function("def create newArrayList foo(int it) {}");
		helper.assertError(function, CREATE_EXTENSION_INFO, DUPLICATE_PARAMETER_NAME, "duplicate", "implicit", "it");
		helper.assertError(function, XTEND_FUNCTION, DUPLICATE_PARAMETER_NAME, "duplicate", "it");
	}
	
	@Test public void testDuplicateParameter_CreateExtension_02() throws Exception {
		XtendFunction function = function("def create result: newArrayList foo(int result) {}");
		helper.assertError(function, CREATE_EXTENSION_INFO, DUPLICATE_PARAMETER_NAME, "duplicate", "result");
		helper.assertError(function, XTEND_FUNCTION, DUPLICATE_PARAMETER_NAME, "duplicate", "result");
	}
	
	@Test public void testRichStringIfPredicate() throws Exception {
		assertNoConformanceError("'''�IF Boolean::FALSE��ENDIF�'''");
		assertNoConformanceError("'''�IF true��ENDIF�'''");
		assertNoConformanceError("'''�IF 1 == 1��ENDIF�'''");
		assertConformanceError("'''�IF 1��ENDIF�'''", XINT_LITERAL, "int",
				"boolean", "java.lang.Boolean");
	}
	
	@Test public void testRichStringForLoop() throws Exception {
		assertNoConformanceError("'''�FOR i: 1..10��ENDFOR�'''");
		assertNoConformanceError("'''�FOR i: 1..10 BEFORE 'a' SEPARATOR 1 AFTER true��ENDFOR�'''");
		assertConformanceError(
				"'''�FOR i: 1..10 BEFORE while(true) null SEPARATOR 'b' AFTER 'c'��ENDFOR�'''", 
				XWHILE_EXPRESSION, "void",
				"java.lang.Object");
		assertConformanceError(
				"'''�FOR i: 1..10 BEFORE 'a' SEPARATOR while(true) null AFTER 'c'��ENDFOR�'''", 
				XWHILE_EXPRESSION, "void",
				"java.lang.Object");
		assertConformanceError(
				"'''�FOR i: 1..10 BEFORE 'a' SEPARATOR null AFTER while(true) null��ENDFOR�'''", 
				XWHILE_EXPRESSION, "void",
				"java.lang.Object");
	}
	
	@Test public void testBug343089_01() throws Exception {
		XtendFunction function = function(
				"def <T extends Integer> noCastRequired() {\n" + 
				 "  [T a,T b|a+b]\n" + 
				 "}");
		helper.assertNoErrors(function);
	}
	
	@Test public void testBug343088_01() throws Exception {
		XtendFunction function = function(
				"def <T extends Integer> (T,T)=>T addFunction() {\n" + 
				"    [T a,T b|a+(b as Integer)]\n" + 
				"}");
		helper.assertError(function, XBLOCK_EXPRESSION, INCOMPATIBLE_RETURN_TYPE, "(T, T)=>T", "(T, T)=>int");
	}
	
	@Test public void testBug343088_02() throws Exception {
		XtendFunction function = function(
				"def <T extends Integer> (T,T)=>T addFunction() {\n" + 
				"    [T a,T b|a+b]\n" + 
				"}");
		helper.assertError(function, XBLOCK_EXPRESSION, INCOMPATIBLE_RETURN_TYPE, "(T, T)=>T", "(T, T)=>int");
	}
	
	@Test public void testBug343088_03() throws Exception {
		XtendFunction function = function(
				"def <T extends Integer> (T,T)=>T addFunction() {\n" + 
				"    [T a,T b|(a+b) as T]\n" + 
				"}");
		helper.assertNoErrors(function);
	}
	
	@Test public void testDispatchFunctionVisibility() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def dispatch foo(String bar) {} def public dispatch foo(Object bar) {}}");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DISPATCH_FUNCTIONS_WITH_DIFFERENT_VISIBILITY, 
				"local", "must", "same", "visibility");
	}
	
	@Test public void testDispatchFunctionStatic1() throws Exception {
		XtendClass xtendClass = clazz("class Foo { def dispatch foo(String bar) {} def static dispatch foo(Object bar) {}}");
		helper.assertError(xtendClass.getMembers().get(0), XTEND_FUNCTION, DISPATCH_FUNCTIONS_MIXED_STATIC_AND_NON_STATIC, 
				"Static", "non-static", "not", "mixed");
	}
	
	@Test public void testDispatchFunctionStatic2() throws Exception {
		Iterator<XtendFile> iter = files(false, 
				 "package test class Bar extends XXX { def dispatch foo(Boolean bar) {} def static dispatch foo(Double bar) {} }"
				,"package test class XXX { def static dispatch foo(String bar) {} def static dispatch foo(Float bar) {}}").iterator();
		waitForAutoBuild();
		helper.assertError(iter.next(), XTEND_FUNCTION, DISPATCH_FUNCTIONS_STATIC_EXPECTED, "must", "be", "static");
	}
	
	@Test public void testDispatchFunctionStatic3() throws Exception {
		Iterator<XtendFile> iter = files(false, 
				"package test class Bar extends XXX { def dispatch foo(Boolean bar) {} def static dispatch foo(Double bar) {} }"
				,"package test class XXX { def dispatch foo(String bar) {} def dispatch foo(Float bar) {}}").iterator();
		waitForAutoBuild();
		helper.assertError(iter.next(), XTEND_FUNCTION, DISPATCH_FUNCTIONS_NON_STATIC_EXPECTED, "must", "not", "be", "static");
	}
	
//	@Test public void testBug343096() throws Exception {
//		XtendFunction function = function(
//				"def <T> test() {\n" + 
//				"  [T t|switch t {\n" + 
//				"    case t:test\n" + 
//				"  }]\n" + 
//				"}");
//		helper.assertNoErrors(function);
//	}

	protected void assertConformanceError(String body, EClass objectType, String... messageParts)
			throws Exception {
		final XtendFunction function = function("def foo() " + body);
		helper.assertError(function, objectType, INCOMPATIBLE_TYPES, messageParts);
	}

	protected void assertCastError(String body, EClass objectType, String... messageParts) throws Exception {
		final XtendFunction function = function("def foo() " + body);
		helper.assertError(function, objectType, INVALID_CAST, messageParts);
	}

	protected void assertNoConformanceError(String body) throws Exception {
		final XtendFunction function = function("def foo() " + body);
		helper.assertNoError(function, INCOMPATIBLE_TYPES);
	}
	
	@Test public void testImportUnused() throws Exception {
		XtendClass clazz = clazz("import java.util.List class X {}");
		helper.assertWarning(clazz.eContainer(), XTEND_IMPORT, IMPORT_UNUSED);
	}
	
	@Test public void testImportUnused_1() throws Exception {
		XtendClass clazz = clazz("import java.util.List class X { private java.util.List sb }");
		helper.assertWarning(clazz.eContainer(), XTEND_IMPORT, IMPORT_UNUSED);
	}
	
	@Test public void testImportUnused_2() throws Exception {
		XtendClass clazz = clazz("import java.util.List class X { private List sb def foo(){sb}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testImportUnused_3() throws Exception {
		XtendClass clazz = clazz("import java.util.Map$Entry class X { private Entry sb def foo(){sb}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testImportUnused_4() throws Exception {
		XtendClass clazz = clazz("import java.util.Map class X { private Map$Entry sb def foo(){sb}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testImportUnused_5() throws Exception {
		XtendClass clazz = clazz("import java.util.Map$Entry class X { private Map$Entry sb def foo(){sb}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testImportDuplicate() throws Exception {
		XtendClass clazz = clazz("import java.util.List import java.util.List class X { private List sb def foo(){sb}}");
		helper.assertWarning(clazz.eContainer(), XTEND_IMPORT, IMPORT_DUPLICATE);
	}
	
	@Test public void testImportWildcard() throws Exception {
		XtendClass clazz = clazz("import java.util.* import java.util.List class X { private List sb def foo(){sb}}");
		helper.assertWarning(clazz.eContainer(), XTEND_IMPORT, IMPORT_WILDCARD_DEPRECATED);
	}
	
	@Test public void testPrivateUnusedField() throws Exception {
		XtendClass clazz = clazz("import java.util.List class X { private List sb }");
		helper.assertWarning(clazz.eContainer(), XTEND_FIELD,FIELD_LOCALLY_NEVER_READ , "not");
	}
	
	@Test public void testUnusedField() throws Exception {
		XtendClass clazz = clazz("import java.util.List class X { protected List foo public List bar}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUnusedFunction() throws Exception {
		XtendClass clazz = clazz("class X { def private foo(String a, Integer b) }");
		helper.assertWarning(clazz, XTEND_FUNCTION, FUNCTION_LOCALLY_NEVER_USED, "method","foo(String, Integer)","never", "used");
	}
	
	@Test public void testUsedFunction() throws Exception {
		XtendClass clazz = clazz("class X { def private foo() def bar(){foo}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUnusedDispatchFunction() throws Exception {
		XtendClass clazz = clazz("class X { def private dispatch foo(String a) def private dispatch foo(Integer a) }");
		helper.assertWarning(clazz, XTEND_FUNCTION, FUNCTION_LOCALLY_NEVER_USED, "method", "foo(Comparable<?>","never", "used");
	}
	
	@Test public void testUsedDispatchFunction() throws Exception {
		XtendClass clazz = clazz("class X { def private dispatch foo(String a) def private dispatch foo(Integer a) def bar(){foo(42)}}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUsedMemberOfExtensionField() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection def foo(){ add('42') }}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUnusedMemberOfExtensionField() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection def foo(){  }}");
		helper.assertWarning(clazz, XTEND_FIELD,FIELD_LOCALLY_NEVER_READ , "not");
	}
	
	@Test public void testUsedMemberOfExtensionFieldWithName() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection bar def foo(){ add('42') }}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUsedMemberOfExtensionFieldWithName_2() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection bar def foo(){ bar.add('42') }}");
		helper.assertNoIssues(clazz.eContainer());
	}
	
	@Test public void testUnusedMemberOfExtensionFieldWithName() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection bar def foo(){  }}");
		helper.assertWarning(clazz, XTEND_FIELD, FIELD_LOCALLY_NEVER_READ , "not");
	}
	
	@Test public void testUnusedMemberOfExtensionField_No_False_Positive() throws Exception {
		XtendClass clazz = clazz("class X { extension java.util.Collection foo def bar(){  newArrayList.add('42') }}");
		helper.assertWarning(clazz, XTEND_FIELD,FIELD_LOCALLY_NEVER_READ , "not");
	}
	
	@Test public void testConstructorThrownExceptionsOfTypeThrowable() throws Exception {
		XtendClass clazz = clazz("class X { new () throws Integer { }}");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, EXCEPTION_NOT_THROWABLE, "No", "can", "subclass", "Throwable");
	}
	
	@Test public void testFunctionThrownExceptionsOfTypeThrowable() throws Exception {
		XtendClass clazz = clazz("class X { def foo() throws Integer { } }");
		helper.assertError(clazz, XTEND_FUNCTION, EXCEPTION_NOT_THROWABLE, "No", "can", "subclass", "Throwable");
	}
	
	@Test public void testExceptionsDeclaredTwiceOnConstructor() throws Exception {
		XtendClass clazz = clazz("import java.io.IOException class X { new () throws IOException, IOException { }}");
		helper.assertError(clazz, XTEND_CONSTRUCTOR, EXCEPTION_DECLARED_TWICE, "Exception", "declared", "twice");
	}
	
	@Test public void testExceptionsNotDeclaredTwiceOnConstructor() throws Exception {
		XtendClass clazz = clazz("import java.io.IOException class X { new () throws IOException, NullPointerException { }}");
		helper.assertNoIssues(clazz);
	}
	
	@Test public void testExceptionsDeclaredTwiceOnFunction() throws Exception {
		XtendClass clazz = clazz("import java.io.IOException class X {def foo() throws IOException, IOException { }}");
		helper.assertError(clazz, XTEND_FUNCTION, EXCEPTION_DECLARED_TWICE, "Exception", "declared", "twice");
	}
	
	@Test public void testExceptionsNotDeclaredTwiceOnFunction() throws Exception {
		XtendClass clazz = clazz("import java.io.IOException class X {def foo() throws IOException, NullPointerException { }}");
		helper.assertNoIssues(clazz);
	}
	
	@Test public void testNoAssignmentsToThisConstructor() throws Exception {
        XtendClass clazz = clazz("class X { new (String y) { this = new X('bar') val x = 2 x = 1}}");
        helper.assertError(clazz, XASSIGNMENT, LEFT_HAND_SIDE_MUST_BE_VARIABLE, "Left-hand side", "must be", "variable");
    }
		    
    @Test public void testNoAssignmentsToThisFunction() throws Exception {
        XtendClass clazz = clazz("class X { def foo(){ this = new X() val x = 1 x = 2}}");
        helper.assertError(clazz, XASSIGNMENT, LEFT_HAND_SIDE_MUST_BE_VARIABLE, "Left-hand side", "must be", "variable");
    }
    
    @Test public void testDispatchNonDispatchClash() throws Exception {
    	XtendClass clazz = clazz("class Foo { def dispatch foo(Object x) {} def foo(int x) {} }");
    	helper.assertWarning(clazz.getMembers().get(0), XTEND_FUNCTION, DISPATCH_PLAIN_FUNCTION_NAME_CLASH, "dispatch", "same", "name", "parameters");
    	helper.assertWarning(clazz.getMembers().get(1), XTEND_FUNCTION, DISPATCH_PLAIN_FUNCTION_NAME_CLASH, "dispatch", "same", "name", "parameters");
    }
    
    @Test public void testKeywordConflict() throws Exception {
    	XtendClass clazz = clazz("class assert { def dispatch volatile(Object x) {} }");
    	helper.assertError(clazz, XTEND_CLASS, INVALID_IDENTIFIER, "assert");
    	helper.assertError(clazz.getMembers().get(0), XTEND_FUNCTION, INVALID_IDENTIFIER, "volatile");
    }
}
