/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.serializer;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IConstraint;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IConstraintContext;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class GrammarConstraintProviderAssignedActionTest extends AbstractXtextTests {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(GrammarConstraintProviderAssignedActionTest.class);

//	private static class ActionFilter2Dot extends GrammarToDot {
//		protected IGrammarNFAProvider<ActionFilterState, ActionFilterTransition> nfaProvider = new ActionFilterNFAProvider();
//
//		@Override
//		protected Node drawAbstractElementTree(AbstractElement ele, Digraph d) {
//			Node n = super.drawAbstractElementTree(ele, d);
//			ActionFilterState nfas = nfaProvider.getNFA(ele);
//
//			for (ActionFilterTransition t : nfas.getOutgoing())
//				d.add(drawFollowerEdge(ele, t, false));
//			for (ActionFilterTransition t : nfas.getOutgoingAfterReturn())
//				d.add(drawFollowerEdge(ele, t, true));
//
//			if (nfas.getOutgoing().size() == 0 && nfas.getOutgoingAfterReturn().size() == 0 && !nfas.isEndState())
//				n.setStyle("dotted");
//			if (nfas.isEndState())
//				n.put("peripheries", "2");
//			return n;
//		}
//
//		protected Edge drawFollowerEdge(AbstractElement ele, ActionFilterTransition t, boolean isParent) {
//			Edge e = new Edge(ele, t.getTarget().getGrammarElement());
//			e.setLabel(String.valueOf(t.getPrecedence()));
//			e.setStyle("dotted");
//			if (isParent)
//				e.put("arrowtail", "odot");
//			if (t.isRuleCall())
//				e.put("arrowhead", "onormalonormal");
//			else
//				e.put("arrowhead", "onormal");
//			return e;
//		}
//	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(XtextStandaloneSetup.class);
	}

	final static String HEADER = "grammar org.eclipse.xtext.validation.GrammarConstraintTestLanguage"
			+ " with org.eclipse.xtext.common.Terminals "
			+ "generate grammarConstraintTest \"http://www.eclipse.org/2010/tmf/xtext/GCT\"  ";

	private String getParserRule(String body) throws Exception {
		Grammar grammar = (Grammar) getModel(HEADER + body);
		IGrammarConstraintProvider gcp = get(IGrammarConstraintProvider.class);

		//		try {
		//			new ActionFilter2Dot().draw(grammar, getName() + ".pdf", "-T pdf");
		//		} catch (IOException e) {
		//			if (log.isDebugEnabled())
		//				log.debug(e.getMessage(), e);
		//		}

		List<IConstraintContext> ctxts = gcp.getConstraints(grammar);
		List<String> result = Lists.newArrayList();
		Set<IConstraint> visited = Sets.newHashSet();
		for (IConstraintContext ctx : ctxts) {
			result.add(ctx.toString());
			for (IConstraint c : ctx.getConstraints())
				if (visited.add(c))
					result.add("  " + c.toString());
		}
		return Joiner.on("\n").join(result);
	}

	@Test public void testXtext() {
		IGrammarConstraintProvider gcp = get(IGrammarConstraintProvider.class);
		List<IConstraintContext> ctxts = gcp.getConstraints(getGrammarAccess().getGrammar());
		//		try {
		//			new ActionFilter2Dot().draw(getGrammarAccess().getGrammar(), getName() + ".pdf", "-T pdf");
		//		} catch (IOException e) {
		//			if (log.isDebugEnabled())
		//				log.debug(e.getMessage(), e);
		//		}
		List<String> result = Lists.newArrayList();
		Set<IConstraint> visited = Sets.newHashSet();
		for (IConstraintContext ctx : ctxts) {
			result.add(ctx.toString());
			for (IConstraint c : ctx.getConstraints())
				if (visited.add(c))
					result.add("  " + c.toString());
		}
		//		System.out.println(Joiner.on("\n").join(result));
	}

	@Test public void testAssignedActionMandatory1() throws Exception {
		String actual = getParserRule("Rule: Foo {Bar.left=current} '+' right=ID; Foo: val=ID;");
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_Bar;\n");
		expected.append("  Rule_Bar returns Bar: (left=Rule_Bar_1 right=ID);\n");
		expected.append("Rule_Bar_1: Foo_Foo;\n");
		expected.append("  Foo_Foo returns Foo: val=ID;\n");
		expected.append("Foo: Foo_Foo;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testAssignedActionMandatory2() throws Exception {
		String actual = getParserRule("Rule: val=ID {Bar.left=current} '+' right=ID;");
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_Bar;\n");
		expected.append("  Rule_Bar returns Bar: (left=Rule_Bar_1 right=ID);\n");
		expected.append("Rule_Bar_1: Rule_Bar_1_Rule;\n");
		expected.append("  Rule_Bar_1_Rule returns Rule: val=ID;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testAssignedActionOptional() throws Exception {
		String actual = getParserRule("Rule: Foo ({Bar.left=current} '+' right=ID)?; Foo: val=ID;");
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Foo_Foo | Rule_Bar;\n");
		expected.append("  Foo_Foo returns Foo: val=ID;\n");
		expected.append("  Rule_Bar returns Bar: (left=Rule_Bar_1_0 right=ID);\n");
		expected.append("Rule_Bar_1_0: Foo_Foo;\n");
		expected.append("Foo: Foo_Foo;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testAssignedActionOptionalMany() throws Exception {
		String actual = getParserRule("Rule: Foo ({Bar.left=current} '+' right=ID)*; Foo: val=ID;");
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Foo_Foo | Rule_Bar;\n");
		expected.append("  Foo_Foo returns Foo: val=ID;\n");
		expected.append("  Rule_Bar returns Bar: (left=Rule_Bar_1_0 right=ID);\n");
		expected.append("Rule_Bar_1_0: Foo_Foo | Rule_Bar;\n");
		expected.append("Foo: Foo_Foo;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testAssignedActionManadatoryMany() throws Exception {
		String actual = getParserRule("Rule: Foo ({Bar.left=current} '+' right=ID)+; Foo: val=ID;");
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_Bar;\n");
		expected.append("  Rule_Bar returns Bar: (left=Rule_Bar_1_0 right=ID);\n");
		expected.append("Rule_Bar_1_0: Foo_Foo | Rule_Bar;\n");
		expected.append("  Foo_Foo returns Foo: val=ID;\n");
		expected.append("Foo: Foo_Foo;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression1() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Addition returns Expr: Prim ({Add.left=current} '+' right=Prim)*;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Addition ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Addition: Addition_Add | Prim_Val;\n");
		expected.append("  Addition_Add returns Add: (left=Addition_Add_1_0 right=Prim);\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Addition_Add_1_0: Addition_Add | Prim_Val;\n");
		expected.append("Prim: Addition_Add | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression2() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Addition returns Expr: Multiplication ({Add.left=current} '+' right=Multiplication)*;\n");
		grammar.append("Multiplication returns Expr: Prim ({Mult.left=current} '*' right=Prim)*;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Addition ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Addition: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("  Addition_Add returns Add: (left=Addition_Add_1_0 right=Multiplication);\n");
		expected.append("  Multiplication_Mult returns Mult: (left=Multiplication_Mult_1_0 right=Prim);\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Addition_Add_1_0: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Multiplication: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Multiplication_Mult_1_0: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Prim: Addition_Add | Multiplication_Mult | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression3() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Addition returns Expr: Prim ({Add.children+=current} ('+' children+=Prim)+)?;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Addition ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Addition: Addition_Add | Prim_Val;\n");
		expected.append("  Addition_Add returns Add: (children+=Addition_Add_1_0 children+=Prim+);\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Addition_Add_1_0: Addition_Add | Prim_Val;\n");
		expected.append("Prim: Addition_Add | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression4() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Addition returns Expr: Multiplication ({Add.addCh+=current} ('+' addCh+=Multiplication)+)?;\n");
		grammar.append("Multiplication returns Expr: Prim ({Mult.mulCh+=current} ('*' mulCh+=Prim)+)?;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Addition ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Addition: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("  Addition_Add returns Add: (addCh+=Addition_Add_1_0 addCh+=Multiplication+);\n");
		expected.append("  Multiplication_Mult returns Mult: (mulCh+=Multiplication_Mult_1_0 mulCh+=Prim+);\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Addition_Add_1_0: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Multiplication: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Multiplication_Mult_1_0: Addition_Add | Multiplication_Mult | Prim_Val;\n");
		expected.append("Prim: Addition_Add | Multiplication_Mult | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression5() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Addition returns Expr: Multiplication ({Bin.left+=current} op='+' right=Multiplication)*;\n");
		grammar.append("Multiplication returns Expr: Prim ({Bin.left+=current} op='*' right=Prim)*;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Addition ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Addition: Addition_Bin | Prim_Val;\n");
		expected.append("  Addition_Bin returns Bin: ((left+=Addition_Bin_1_0 op='+' right=Multiplication) | (left+=Multiplication_Bin_1_0 op='*' right=Prim));\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Addition_Bin_1_0: Addition_Bin | Prim_Val;\n");
		expected.append("Multiplication: Addition_Bin | Prim_Val;\n");
		expected.append("Multiplication_Bin_1_0: Addition_Bin | Prim_Val;\n");
		expected.append("Prim: Addition_Bin | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testExpression6() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Assignment returns Expr: Addition ({Bin.left+=current} op='=' right=Addition)*;\n");
		grammar.append("Addition returns Expr: Multiplication ({Bin.left+=current} op='+' right=Multiplication)*;\n");
		grammar.append("Multiplication returns Expr: Prim ({Bin.left+=current} op='*' right=Prim)*;\n");
		grammar.append("Prim returns Expr: {Val} name=ID | '(' Assignment ')';\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Assignment: Addition_Bin | Prim_Val;\n");
		expected.append("  Addition_Bin returns Bin: (\n");
		expected.append("    (left+=Addition_Bin_1_0 op='+' right=Multiplication) | \n");
		expected.append("    (left+=Multiplication_Bin_1_0 op='*' right=Prim) | \n");
		expected.append("    (left+=Assignment_Bin_1_0 op='=' right=Addition)\n");
		expected.append(");\n");
		expected.append("  Prim_Val returns Val: name=ID;\n");
		expected.append("Assignment_Bin_1_0: Addition_Bin | Prim_Val;\n");
		expected.append("Addition: Addition_Bin | Prim_Val;\n");
		expected.append("Addition_Bin_1_0: Addition_Bin | Prim_Val;\n");
		expected.append("Multiplication: Addition_Bin | Prim_Val;\n");
		expected.append("Multiplication_Bin_1_0: Addition_Bin | Prim_Val;\n");
		expected.append("Prim: Addition_Bin | Prim_Val;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSequence1() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: val1=ID {A.a1=current} a2=ID {B.b1=current} b2=ID {C.c1=current} c2=ID;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_C;\n");
		expected.append("  Rule_C returns C: (c1=Rule_C_5 c2=ID);\n");
		expected.append("Rule_A_1: Rule_A_1_Rule;\n");
		expected.append("  Rule_A_1_Rule returns Rule: val1=ID;\n");
		expected.append("Rule_B_3: Rule_B_3_A;\n");
		expected.append("  Rule_B_3_A returns A: (a1=Rule_A_1 a2=ID);\n");
		expected.append("Rule_C_5: Rule_C_5_B;\n");
		expected.append("  Rule_C_5_B returns B: (b1=Rule_B_3 b2=ID);");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSequence2() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: val1=ID {A.a1=current} a2=ID {A.a1=current} a2=ID {A.a1=current} a2=ID;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_A;\n");
		expected.append("  Rule_A returns A: (a1=Rule_A_5 a2=ID);\n");
		expected.append("Rule_A_1: Rule_A_1_Rule;\n");
		expected.append("  Rule_A_1_Rule returns Rule: val1=ID;\n");
		expected.append("Rule_A_3: Rule_A_3_A;\n");
		expected.append("  Rule_A_3_A returns A: (a1=Rule_A_1 a2=ID);\n");
		expected.append("Rule_A_5: Rule_A_5_A;\n");
		expected.append("  Rule_A_5_A returns A: (a1=Rule_A_3 a2=ID);");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSequence3() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: val1=ID ({A.a1=current} a2=ID ({A.a1=current} a2=ID ({A.a1=current} a2=ID)?)?)?;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_A | Rule_Rule;\n");
		expected.append("  Rule_A returns A: ((a1=Rule_A_1_2_2_0 a2=ID) | (a1=Rule_A_1_2_0 a2=ID) | (a1=Rule_A_1_0 a2=ID));\n");
		expected.append("  Rule_Rule returns Rule: val1=ID;\n");
		expected.append("Rule_A_1_0: Rule_Rule;\n");
		expected.append("Rule_A_1_2_0: Rule_A_1_2_0_A;\n");
		expected.append("  Rule_A_1_2_0_A returns A: (a1=Rule_A_1_0 a2=ID);\n");
		expected.append("Rule_A_1_2_2_0: Rule_A_1_2_2_0_A;\n");
		expected.append("  Rule_A_1_2_2_0_A returns A: (a1=Rule_A_1_2_0 a2=ID);");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSequence4() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: {A1} ({A2.left=current})+;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_A2;\n");
		expected.append("  Rule_A2 returns A2: left=Rule_A2_1;\n");
		expected.append("Rule_A2_1: Rule_A2 | Rule_A2_1_A1;\n");
		expected.append("  Rule_A2_1_A1 returns A1: {A1};");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSequence5() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: INT? {Bar} 'bar' ({FooBar.bar=current} 'act')?;");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_Bar | Rule_FooBar;\n");
		expected.append("  Rule_Bar returns Bar: {Bar};\n");
		expected.append("  Rule_FooBar returns FooBar: bar=Rule_FooBar_3_0;\n");
		expected.append("Rule_FooBar_3_0: Rule_Bar;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionAlternative1() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Rule: root=ID (val1=ID | {A.a1=current} a2=ID | {B.b1=current} b2=ID | {C.c1=current} c2=ID);\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Rule: Rule_A | Rule_B | Rule_C | Rule_Rule;\n");
		expected.append("  Rule_A returns A: (a1=Rule_A_1_1_0 a2=ID);\n");
		expected.append("  Rule_B returns B: (b1=Rule_B_1_2_0 b2=ID);\n");
		expected.append("  Rule_C returns C: (c1=Rule_C_1_3_0 c2=ID);\n");
		expected.append("  Rule_Rule returns Rule: (root=ID val1=ID);\n");
		expected.append("Rule_A_1_1_0: Rule_A_1_1_0_B_1_2_0_C_1_3_0_Rule;\n");
		expected.append("  Rule_A_1_1_0_B_1_2_0_C_1_3_0_Rule returns Rule: root=ID;\n");
		expected.append("Rule_B_1_2_0: Rule_A_1_1_0_B_1_2_0_C_1_3_0_Rule;\n");
		expected.append("Rule_C_1_3_0: Rule_A_1_1_0_B_1_2_0_C_1_3_0_Rule;");
		assertEquals(expected.toString(), actual);
	}

	/*
	XMemberFeatureCall returns XExpression:
	XPrimaryExpression
	(=>({XAssignment.assignable=current} '.' feature=[types::JvmIdentifiableElement] OpSingleAssign) value=XAssignment
	|=>({XMemberFeatureCall.memberCallTarget=current} ("."|nullSafe?="?."|spreading?="*.")) 
		('<' typeArguments+=JvmArgumentTypeReference (',' typeArguments+=JvmArgumentTypeReference)* '>')?  
		feature=[types::JvmIdentifiableElement] (
			=>explicitOperationCall?='(' 
				(
				    memberCallArguments+=XShortClosure
				  |	memberCallArguments+=XExpression (',' memberCallArguments+=XExpression)*
				)? 
			')')?
		)*;
	 */
	@Test public void testActionAlternative2() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Model: Bar ({Foo.f1=current} f2=ID f3=ID? f4=ID)*; Bar: bar=ID;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Model: Bar_Bar | Model_Foo;\n");
		expected.append("  Bar_Bar returns Bar: bar=ID;\n");
		expected.append("  Model_Foo returns Foo: (f1=Model_Foo_1_0 f2=ID f3=ID? f4=ID);\n");
		expected.append("Model_Foo_1_0: Bar_Bar | Model_Foo;\n");
		expected.append("Bar: Bar_Bar;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionAlternative3() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Model: {Foo} foo=ID ({Bar.bar=current} bar2=ID | {Baz.baz=current} baz2=ID);");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Model: Model_Bar | Model_Baz;\n");
		expected.append("  Model_Bar returns Bar: (bar=Model_Bar_2_0_0 bar2=ID);\n");
		expected.append("  Model_Baz returns Baz: (baz=Model_Baz_2_1_0 baz2=ID);\n");
		expected.append("Model_Bar_2_0_0: Model_Bar_2_0_0_Baz_2_1_0_Foo;\n");
		expected.append("  Model_Bar_2_0_0_Baz_2_1_0_Foo returns Foo: foo=ID;\n");
		expected.append("Model_Baz_2_1_0: Model_Bar_2_0_0_Baz_2_1_0_Foo;");
		assertEquals(expected.toString(), actual);
	}

	@Test public void testActionSingleAndAssigned() throws Exception {
		StringBuilder grammar = new StringBuilder();
		grammar.append("Model: {Act1} act1=ID | Foo ({Act2.left=current} act2=ID)?;\n");
		grammar.append("Foo: foo=ID {Act1.f1=current} act1=ID val=ID;\n");
		String actual = getParserRule(grammar.toString());
		StringBuilder expected = new StringBuilder();
		expected.append("Model: Model_Act1 | Model_Act2;\n");
		expected.append("  Model_Act1 returns Act1: (act1=ID | (f1=Foo_Act1_1 act1=ID val=ID));\n");
		expected.append("  Model_Act2 returns Act2: (left=Model_Act2_1_1_0 act2=ID);\n");
		expected.append("Model_Act2_1_1_0: Foo_Act1;\n");
		expected.append("  Foo_Act1 returns Act1: (f1=Foo_Act1_1 act1=ID val=ID);\n");
		expected.append("Foo: Foo_Act1;\n");
		expected.append("Foo_Act1_1: Foo_Act1_1_Foo;\n");
		expected.append("  Foo_Act1_1_Foo returns Foo: foo=ID;");
		assertEquals(expected.toString(), actual);
	}
}
