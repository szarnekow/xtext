/*
* generated by Xtext
*/
grammar InternalSyntacticSequencerTestLanguage;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.xtext.serializer.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.xtext.serializer.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.serializer.services.SyntacticSequencerTestLanguageGrammarAccess;

}

@parser::members {

 	private SyntacticSequencerTestLanguageGrammarAccess grammarAccess;
 	
    public InternalSyntacticSequencerTestLanguageParser(TokenStream input, SyntacticSequencerTestLanguageGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Model";	
   	}
   	
   	@Override
   	protected SyntacticSequencerTestLanguageGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	 iv_ruleModel=ruleModel 
	 { $current=$iv_ruleModel.current; } 
	 EOF 
;

// Rule Model
ruleModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getX1MandatoryKeywordsParserRuleCall_0_0()); 
	    }
		lv_x1_0_0=ruleMandatoryKeywords		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"x1",
        		lv_x1_0_0, 
        		"MandatoryKeywords");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getX2Exp0ParserRuleCall_1_0()); 
	    }
		lv_x2_1_0=ruleExp0		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"x2",
        		lv_x2_1_0, 
        		"Exp0");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getX3Exp1ParserRuleCall_2_0()); 
	    }
		lv_x3_2_0=ruleExp1		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"x3",
        		lv_x3_2_0, 
        		"Exp1");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getX4Exp2ParserRuleCall_3_0()); 
	    }
		lv_x4_3_0=ruleExp2		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"x4",
        		lv_x4_3_0, 
        		"Exp2");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleMandatoryKeywords
entryRuleMandatoryKeywords returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMandatoryKeywordsRule()); }
	 iv_ruleMandatoryKeywords=ruleMandatoryKeywords 
	 { $current=$iv_ruleMandatoryKeywords.current; } 
	 EOF 
;

// Rule MandatoryKeywords
ruleMandatoryKeywords returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='#1' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getMandatoryKeywordsAccess().getNumberSignDigitOneKeyword_0());
    }
(
(
		lv_val1_1_0=RULE_ID
		{
			newLeafNode(lv_val1_1_0, grammarAccess.getMandatoryKeywordsAccess().getVal1IDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getMandatoryKeywordsRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"val1",
        		lv_val1_1_0, 
        		"ID");
	    }

)
)	otherlv_2='kw1' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMandatoryKeywordsAccess().getKw1Keyword_2());
    }
(
(
		lv_val2_3_0=RULE_ID
		{
			newLeafNode(lv_val2_3_0, grammarAccess.getMandatoryKeywordsAccess().getVal2IDTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getMandatoryKeywordsRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"val2",
        		lv_val2_3_0, 
        		"ID");
	    }

)
)	otherlv_4='kw2' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getMandatoryKeywordsAccess().getKw2Keyword_4());
    }
	otherlv_5='kw3' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getMandatoryKeywordsAccess().getKw3Keyword_5());
    }
(
(
		lv_val3_6_0=RULE_ID
		{
			newLeafNode(lv_val3_6_0, grammarAccess.getMandatoryKeywordsAccess().getVal3IDTerminalRuleCall_6_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getMandatoryKeywordsRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"val3",
        		lv_val3_6_0, 
        		"ID");
	    }

)
)	otherlv_7='kw4' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getMandatoryKeywordsAccess().getKw4Keyword_7());
    }
)
;





// Entry rule entryRuleExp0
entryRuleExp0 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getExp0Rule()); }
	 iv_ruleExp0=ruleExp0 
	 { $current=$iv_ruleExp0.current; } 
	 EOF 
;

// Rule Exp0
ruleExp0 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='#2' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getExp0Access().getNumberSignDigitTwoKeyword_0());
    }

    { 
        newCompositeNode(grammarAccess.getExp0Access().getAddition0ParserRuleCall_1()); 
    }
    this_Addition0_1=ruleAddition0
    { 
        $current = $this_Addition0_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleAddition0
entryRuleAddition0 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAddition0Rule()); }
	 iv_ruleAddition0=ruleAddition0 
	 { $current=$iv_ruleAddition0.current; } 
	 EOF 
;

// Rule Addition0
ruleAddition0 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAddition0Access().getPrim0ParserRuleCall_0()); 
    }
    this_Prim0_0=rulePrim0
    { 
        $current = $this_Prim0_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAddition0Access().getAdd0LeftAction_1_0(),
            $current);
    }
)	otherlv_2='+' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAddition0Access().getPlusSignKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAddition0Access().getRightPrim0ParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=rulePrim0		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAddition0Rule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"Prim0");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRulePrim0
entryRulePrim0 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPrim0Rule()); }
	 iv_rulePrim0=rulePrim0 
	 { $current=$iv_rulePrim0.current; } 
	 EOF 
;

// Rule Prim0
rulePrim0 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getPrim0Access().getVal0Action_0(),
            $current);
    }
)(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getPrim0Access().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPrim0Rule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
))
;





// Entry rule entryRuleExp1
entryRuleExp1 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getExp1Rule()); }
	 iv_ruleExp1=ruleExp1 
	 { $current=$iv_ruleExp1.current; } 
	 EOF 
;

// Rule Exp1
ruleExp1 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='#3' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getExp1Access().getNumberSignDigitThreeKeyword_0());
    }

    { 
        newCompositeNode(grammarAccess.getExp1Access().getAddition1ParserRuleCall_1()); 
    }
    this_Addition1_1=ruleAddition1
    { 
        $current = $this_Addition1_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleAddition1
entryRuleAddition1 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAddition1Rule()); }
	 iv_ruleAddition1=ruleAddition1 
	 { $current=$iv_ruleAddition1.current; } 
	 EOF 
;

// Rule Addition1
ruleAddition1 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAddition1Access().getPrim1ParserRuleCall_0()); 
    }
    this_Prim1_0=rulePrim1
    { 
        $current = $this_Prim1_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAddition1Access().getAdd1LeftAction_1_0(),
            $current);
    }
)	otherlv_2='+' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAddition1Access().getPlusSignKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAddition1Access().getRightPrim1ParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=rulePrim1		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAddition1Rule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"Prim1");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRulePrim1
entryRulePrim1 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPrim1Rule()); }
	 iv_rulePrim1=rulePrim1 
	 { $current=$iv_rulePrim1.current; } 
	 EOF 
;

// Rule Prim1
rulePrim1 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
    {
        $current = forceCreateModelElement(
            grammarAccess.getPrim1Access().getVal1Action_0_0(),
            $current);
    }
)(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getPrim1Access().getNameIDTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPrim1Rule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
))
    |(	otherlv_2='(' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPrim1Access().getLeftParenthesisKeyword_1_0());
    }

    { 
        newCompositeNode(grammarAccess.getPrim1Access().getAddition1ParserRuleCall_1_1()); 
    }
    this_Addition1_3=ruleAddition1
    { 
        $current = $this_Addition1_3.current; 
        afterParserOrEnumRuleCall();
    }
	otherlv_4=')' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPrim1Access().getRightParenthesisKeyword_1_2());
    }
))
;





// Entry rule entryRuleExp2
entryRuleExp2 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getExp2Rule()); }
	 iv_ruleExp2=ruleExp2 
	 { $current=$iv_ruleExp2.current; } 
	 EOF 
;

// Rule Exp2
ruleExp2 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='#4' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getExp2Access().getNumberSignDigitFourKeyword_0());
    }

    { 
        newCompositeNode(grammarAccess.getExp2Access().getAddition2ParserRuleCall_1()); 
    }
    this_Addition2_1=ruleAddition2
    { 
        $current = $this_Addition2_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleAddition2
entryRuleAddition2 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAddition2Rule()); }
	 iv_ruleAddition2=ruleAddition2 
	 { $current=$iv_ruleAddition2.current; } 
	 EOF 
;

// Rule Addition2
ruleAddition2 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAddition2Access().getMultiplication2ParserRuleCall_0()); 
    }
    this_Multiplication2_0=ruleMultiplication2
    { 
        $current = $this_Multiplication2_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAddition2Access().getAdd2LeftAction_1_0(),
            $current);
    }
)	otherlv_2='+' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAddition2Access().getPlusSignKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAddition2Access().getRightMultiplication2ParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=ruleMultiplication2		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAddition2Rule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"Multiplication2");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleMultiplication2
entryRuleMultiplication2 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMultiplication2Rule()); }
	 iv_ruleMultiplication2=ruleMultiplication2 
	 { $current=$iv_ruleMultiplication2.current; } 
	 EOF 
;

// Rule Multiplication2
ruleMultiplication2 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getMultiplication2Access().getPrim2ParserRuleCall_0()); 
    }
    this_Prim2_0=rulePrim2
    { 
        $current = $this_Prim2_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getMultiplication2Access().getMult2LeftAction_1_0(),
            $current);
    }
)	otherlv_2='*' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMultiplication2Access().getAsteriskKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMultiplication2Access().getRightPrim2ParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=rulePrim2		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMultiplication2Rule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"Prim2");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRulePrim2
entryRulePrim2 returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPrim2Rule()); }
	 iv_rulePrim2=rulePrim2 
	 { $current=$iv_rulePrim2.current; } 
	 EOF 
;

// Rule Prim2
rulePrim2 returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
    {
        $current = forceCreateModelElement(
            grammarAccess.getPrim2Access().getVal2Action_0_0(),
            $current);
    }
)(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getPrim2Access().getNameIDTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPrim2Rule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
))
    |(	otherlv_2='(' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPrim2Access().getLeftParenthesisKeyword_1_0());
    }

    { 
        newCompositeNode(grammarAccess.getPrim2Access().getAddition2ParserRuleCall_1_1()); 
    }
    this_Addition2_3=ruleAddition2
    { 
        $current = $this_Addition2_3.current; 
        afterParserOrEnumRuleCall();
    }
	otherlv_4=')' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPrim2Access().getRightParenthesisKeyword_1_2());
    }
))
;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

