/*
* generated by Xtext
*/

package org.eclipse.xtext.ui.tests.editor.contentassist.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class LookAheadContentAssistTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Model");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cModelAction_0 = (Action)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Assignment cAttributeAssignment_1_0_1 = (Assignment)cGroup_1_0.eContents().get(1);
		private final RuleCall cAttributeAttributeParserRuleCall_1_0_1_0 = (RuleCall)cAttributeAssignment_1_0_1.eContents().get(0);
		private final Assignment cAttributeAssignment_1_0_2 = (Assignment)cGroup_1_0.eContents().get(2);
		private final RuleCall cAttributePairParserRuleCall_1_0_2_0 = (RuleCall)cAttributeAssignment_1_0_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_1_0_3 = (Keyword)cGroup_1_0.eContents().get(3);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final Keyword cLeftSquareBracketKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cAttributeAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cAttributeAttributeParserRuleCall_1_1_1_0 = (RuleCall)cAttributeAssignment_1_1_1.eContents().get(0);
		private final Assignment cAttributeAssignment_1_1_2 = (Assignment)cGroup_1_1.eContents().get(2);
		private final RuleCall cAttributePairParserRuleCall_1_1_2_0 = (RuleCall)cAttributeAssignment_1_1_2.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_1_1_3 = (Keyword)cGroup_1_1.eContents().get(3);
		private final Group cGroup_1_2 = (Group)cAlternatives_1.eContents().get(2);
		private final Keyword cLessThanSignKeyword_1_2_0 = (Keyword)cGroup_1_2.eContents().get(0);
		private final Assignment cAttributeAssignment_1_2_1 = (Assignment)cGroup_1_2.eContents().get(1);
		private final RuleCall cAttributeAttributeParserRuleCall_1_2_1_0 = (RuleCall)cAttributeAssignment_1_2_1.eContents().get(0);
		private final Assignment cAttributeAssignment_1_2_2 = (Assignment)cGroup_1_2.eContents().get(2);
		private final RuleCall cAttributePairParserRuleCall_1_2_2_0 = (RuleCall)cAttributeAssignment_1_2_2.eContents().get(0);
		private final Keyword cGreaterThanSignKeyword_1_2_3 = (Keyword)cGroup_1_2.eContents().get(3);
		private final Group cGroup_1_3 = (Group)cAlternatives_1.eContents().get(3);
		private final Keyword cLeftCurlyBracketKeyword_1_3_0 = (Keyword)cGroup_1_3.eContents().get(0);
		private final Assignment cAttributeAssignment_1_3_1 = (Assignment)cGroup_1_3.eContents().get(1);
		private final RuleCall cAttributeAttributeParserRuleCall_1_3_1_0 = (RuleCall)cAttributeAssignment_1_3_1.eContents().get(0);
		private final Assignment cAttributeAssignment_1_3_2 = (Assignment)cGroup_1_3.eContents().get(2);
		private final RuleCall cAttributePairParserRuleCall_1_3_2_0 = (RuleCall)cAttributeAssignment_1_3_2.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_1_3_3 = (Keyword)cGroup_1_3.eContents().get(3);
		
		//Model:
		//	{Model} ("(" attribute+=Attribute* attribute+=Pair* ")" | "[" attribute+=Attribute+ attribute+=Pair* "]" | "<"
		//	attribute+=Attribute* attribute+=Pair+ ">" | "{" attribute+=Attribute+ attribute+=Pair+ "}");
		public ParserRule getRule() { return rule; }

		//{Model} ("(" attribute+=Attribute* attribute+=Pair* ")" | "[" attribute+=Attribute+ attribute+=Pair* "]" | "<"
		//attribute+=Attribute* attribute+=Pair+ ">" | "{" attribute+=Attribute+ attribute+=Pair+ "}")
		public Group getGroup() { return cGroup; }

		//{Model}
		public Action getModelAction_0() { return cModelAction_0; }

		//"(" attribute+=Attribute* attribute+=Pair* ")" | "[" attribute+=Attribute+ attribute+=Pair* "]" | "<"
		//attribute+=Attribute* attribute+=Pair+ ">" | "{" attribute+=Attribute+ attribute+=Pair+ "}"
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//"(" attribute+=Attribute* attribute+=Pair* ")"
		public Group getGroup_1_0() { return cGroup_1_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1_0_0() { return cLeftParenthesisKeyword_1_0_0; }

		//attribute+=Attribute*
		public Assignment getAttributeAssignment_1_0_1() { return cAttributeAssignment_1_0_1; }

		//Attribute
		public RuleCall getAttributeAttributeParserRuleCall_1_0_1_0() { return cAttributeAttributeParserRuleCall_1_0_1_0; }

		//attribute+=Pair*
		public Assignment getAttributeAssignment_1_0_2() { return cAttributeAssignment_1_0_2; }

		//Pair
		public RuleCall getAttributePairParserRuleCall_1_0_2_0() { return cAttributePairParserRuleCall_1_0_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_1_0_3() { return cRightParenthesisKeyword_1_0_3; }

		//"[" attribute+=Attribute+ attribute+=Pair* "]"
		public Group getGroup_1_1() { return cGroup_1_1; }

		//"["
		public Keyword getLeftSquareBracketKeyword_1_1_0() { return cLeftSquareBracketKeyword_1_1_0; }

		//attribute+=Attribute+
		public Assignment getAttributeAssignment_1_1_1() { return cAttributeAssignment_1_1_1; }

		//Attribute
		public RuleCall getAttributeAttributeParserRuleCall_1_1_1_0() { return cAttributeAttributeParserRuleCall_1_1_1_0; }

		//attribute+=Pair*
		public Assignment getAttributeAssignment_1_1_2() { return cAttributeAssignment_1_1_2; }

		//Pair
		public RuleCall getAttributePairParserRuleCall_1_1_2_0() { return cAttributePairParserRuleCall_1_1_2_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_1_1_3() { return cRightSquareBracketKeyword_1_1_3; }

		//"<" attribute+=Attribute* attribute+=Pair+ ">"
		public Group getGroup_1_2() { return cGroup_1_2; }

		//"<"
		public Keyword getLessThanSignKeyword_1_2_0() { return cLessThanSignKeyword_1_2_0; }

		//attribute+=Attribute*
		public Assignment getAttributeAssignment_1_2_1() { return cAttributeAssignment_1_2_1; }

		//Attribute
		public RuleCall getAttributeAttributeParserRuleCall_1_2_1_0() { return cAttributeAttributeParserRuleCall_1_2_1_0; }

		//attribute+=Pair+
		public Assignment getAttributeAssignment_1_2_2() { return cAttributeAssignment_1_2_2; }

		//Pair
		public RuleCall getAttributePairParserRuleCall_1_2_2_0() { return cAttributePairParserRuleCall_1_2_2_0; }

		//">"
		public Keyword getGreaterThanSignKeyword_1_2_3() { return cGreaterThanSignKeyword_1_2_3; }

		//"{" attribute+=Attribute+ attribute+=Pair+ "}"
		public Group getGroup_1_3() { return cGroup_1_3; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_1_3_0() { return cLeftCurlyBracketKeyword_1_3_0; }

		//attribute+=Attribute+
		public Assignment getAttributeAssignment_1_3_1() { return cAttributeAssignment_1_3_1; }

		//Attribute
		public RuleCall getAttributeAttributeParserRuleCall_1_3_1_0() { return cAttributeAttributeParserRuleCall_1_3_1_0; }

		//attribute+=Pair+
		public Assignment getAttributeAssignment_1_3_2() { return cAttributeAssignment_1_3_2; }

		//Pair
		public RuleCall getAttributePairParserRuleCall_1_3_2_0() { return cAttributePairParserRuleCall_1_3_2_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_1_3_3() { return cRightCurlyBracketKeyword_1_3_3; }
	}

	public class AttributeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Attribute");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cValueIDTerminalRuleCall_0 = (RuleCall)cValueAssignment.eContents().get(0);
		
		//Attribute:
		//	value=ID;
		public ParserRule getRule() { return rule; }

		//value=ID
		public Assignment getValueAssignment() { return cValueAssignment; }

		//ID
		public RuleCall getValueIDTerminalRuleCall_0() { return cValueIDTerminalRuleCall_0; }
	}

	public class PairElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Pair");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueIDTerminalRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		
		////Model : '(' (attribute+=IdAttribute* | attribute+=StringAttribute*) (attribute+=Pair)* ')';
		////
		////Attribute:
		////		IdAttribute | StringAttribute;
		////	
		////IdAttribute:
		////        value=ID;
		////
		////StringAttribute:
		////        value=STRING;
		////        
		////Pair:
		////        name=ID "=" value=ID;
		//Pair:
		//	name=ID "=" value=ID;
		public ParserRule getRule() { return rule; }

		//name=ID "=" value=ID //Model : '(' (attribute+=IdAttribute* | attribute+=StringAttribute*) (attribute+=Pair)* ')';
		////
		////Attribute:
		////		IdAttribute | StringAttribute;
		////	
		////IdAttribute:
		////        value=ID;
		////
		////StringAttribute:
		////        value=STRING;
		////        
		////Pair:
		////        name=ID "=" value=ID;
		public Group getGroup() { return cGroup; }

		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }

		////Model : '(' (attribute+=IdAttribute* | attribute+=StringAttribute*) (attribute+=Pair)* ')';
		////
		////Attribute:
		////		IdAttribute | StringAttribute;
		////	
		////IdAttribute:
		////        value=ID;
		////
		////StringAttribute:
		////        value=STRING;
		////        
		////Pair:
		////        name=ID "=" value=ID;
		//value=ID
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		////Model : '(' (attribute+=IdAttribute* | attribute+=StringAttribute*) (attribute+=Pair)* ')';
		////
		////Attribute:
		////		IdAttribute | StringAttribute;
		////	
		////IdAttribute:
		////        value=ID;
		////
		////StringAttribute:
		////        value=STRING;
		////        
		////Pair:
		////        name=ID "=" value=ID;
		//ID
		public RuleCall getValueIDTerminalRuleCall_2_0() { return cValueIDTerminalRuleCall_2_0; }
	}
	
	
	private ModelElements pModel;
	private AttributeElements pAttribute;
	private PairElements pPair;
	
	private final GrammarProvider grammarProvider;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public LookAheadContentAssistTestLanguageGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammarProvider = grammarProvider;
		this.gaTerminals = gaTerminals;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Model:
	//	{Model} ("(" attribute+=Attribute* attribute+=Pair* ")" | "[" attribute+=Attribute+ attribute+=Pair* "]" | "<"
	//	attribute+=Attribute* attribute+=Pair+ ">" | "{" attribute+=Attribute+ attribute+=Pair+ "}");
	public ModelElements getModelAccess() {
		return (pModel != null) ? pModel : (pModel = new ModelElements());
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}

	//Attribute:
	//	value=ID;
	public AttributeElements getAttributeAccess() {
		return (pAttribute != null) ? pAttribute : (pAttribute = new AttributeElements());
	}
	
	public ParserRule getAttributeRule() {
		return getAttributeAccess().getRule();
	}

	////Model : '(' (attribute+=IdAttribute* | attribute+=StringAttribute*) (attribute+=Pair)* ')';
	////
	////Attribute:
	////		IdAttribute | StringAttribute;
	////	
	////IdAttribute:
	////        value=ID;
	////
	////StringAttribute:
	////        value=STRING;
	////        
	////Pair:
	////        name=ID "=" value=ID;
	//Pair:
	//	name=ID "=" value=ID;
	public PairElements getPairAccess() {
		return (pPair != null) ? pPair : (pPair = new PairElements());
	}
	
	public ParserRule getPairRule() {
		return getPairAccess().getRule();
	}

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
