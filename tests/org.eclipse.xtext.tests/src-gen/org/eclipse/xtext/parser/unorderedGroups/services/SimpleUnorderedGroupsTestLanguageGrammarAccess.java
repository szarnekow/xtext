/*
* generated by Xtext
*/

package org.eclipse.xtext.parser.unorderedGroups.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.parser.unorderedGroups.services.UnorderedGroupsTestLanguageGrammarAccess;

@Singleton
public class SimpleUnorderedGroupsTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class DelegateModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "DelegateModel");
		private final RuleCall cModelParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//DelegateModel returns Model:
		//	Model;
		public ParserRule getRule() { return rule; }

		//Model
		public RuleCall getModelParserRuleCall() { return cModelParserRuleCall; }
	}
	
	
	private DelegateModelElements pDelegateModel;
	
	private final GrammarProvider grammarProvider;

	private UnorderedGroupsTestLanguageGrammarAccess gaUnorderedGroupsTestLanguage;

	@Inject
	public SimpleUnorderedGroupsTestLanguageGrammarAccess(GrammarProvider grammarProvider,
		UnorderedGroupsTestLanguageGrammarAccess gaUnorderedGroupsTestLanguage) {
		this.grammarProvider = grammarProvider;
		this.gaUnorderedGroupsTestLanguage = gaUnorderedGroupsTestLanguage;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public UnorderedGroupsTestLanguageGrammarAccess getUnorderedGroupsTestLanguageGrammarAccess() {
		return gaUnorderedGroupsTestLanguage;
	}

	
	//DelegateModel returns Model:
	//	Model;
	public DelegateModelElements getDelegateModelAccess() {
		return (pDelegateModel != null) ? pDelegateModel : (pDelegateModel = new DelegateModelElements());
	}
	
	public ParserRule getDelegateModelRule() {
		return getDelegateModelAccess().getRule();
	}

	//Model:
	//	{Model} ("1" (first?="a" & second?="b") | "2" (first?="a" & second?="b" & third?="c" & forth?="d") | "3" (first?="a"?
	//	& second?="b"?) | "4" (first?="a" & second?="b"?) | "5" (first?="a"? & second?="b") | "6" (firstAsList+="a"+ &
	//	secondAsList+="b"*) | "7" (firstAsList+="a"* & secondAsList+="b"+) | "8" ("a" "b" first?="c" & "a" "b" second?="d") |
	//	"9" (firstAsList+="a" & secondAsList+="b")+ | "10" (firstAsList+="a" & secondAsList+="b")* | "11" (first?="a" &
	//	second?="b")? | "12" (firstAsList+="a" & secondAsList+="b") (firstAsList+="a" & secondAsList+="b") | "13"
	//	(firstAsList+="a" & secondAsList+="b")? firstAsList+="a" secondAsList+="b" | "14" ((firstAsList+="a" &
	//	secondAsList+="b") & (thirdAsList+="c" & forthAsList+="d"))+ | "datatypes" value=UnorderedDatatype | "serialization"
	//	serialized=UnorderedSerialization | "bug302585" nestedModel+=NestedModel*);
	public UnorderedGroupsTestLanguageGrammarAccess.ModelElements getModelAccess() {
		return gaUnorderedGroupsTestLanguage.getModelAccess();
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}

	//NestedModel:
	//	{NestedModel} (first?="a" & second?="b")? "nested";
	public UnorderedGroupsTestLanguageGrammarAccess.NestedModelElements getNestedModelAccess() {
		return gaUnorderedGroupsTestLanguage.getNestedModelAccess();
	}
	
	public ParserRule getNestedModelRule() {
		return getNestedModelAccess().getRule();
	}

	//UnorderedDatatype:
	//	"1" ("a" & "b") | "2" ("a" & "b" & "c" & "d") | "3" ("a"? & "b"?) | "4" ("a" & "b"?) | "5" ("a"? & "b") | "6" ("a"+ &
	//	"b"*) | "7" ("a"* & "b"+) | "8" ("a" "b" "c" & "a" "b" "d") | "9" ("a" & "b")+ | "10" ("a" & "b")* | "11" ("a" & "b")?
	//	| "12" ("a" & "b") ("a" & "b") | "13" ("a" & "b")? "a" "b" | "14" (("a" & "b") & ("c" & "d"))+;
	public UnorderedGroupsTestLanguageGrammarAccess.UnorderedDatatypeElements getUnorderedDatatypeAccess() {
		return gaUnorderedGroupsTestLanguage.getUnorderedDatatypeAccess();
	}
	
	public ParserRule getUnorderedDatatypeRule() {
		return getUnorderedDatatypeAccess().getRule();
	}

	//UnorderedSerialization:
	//	{UnorderedSerialization} ("1" first?="a"? & second?="b"? & third?="c"? & forth?="d"? | "2" (firstAsList+="a" &
	//	secondAsList+="b")* | "3" (firstAsList+="a"+ & second?="b")*);
	public UnorderedGroupsTestLanguageGrammarAccess.UnorderedSerializationElements getUnorderedSerializationAccess() {
		return gaUnorderedGroupsTestLanguage.getUnorderedSerializationAccess();
	}
	
	public ParserRule getUnorderedSerializationRule() {
		return getUnorderedSerializationAccess().getRule();
	}

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaUnorderedGroupsTestLanguage.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaUnorderedGroupsTestLanguage.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaUnorderedGroupsTestLanguage.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaUnorderedGroupsTestLanguage.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaUnorderedGroupsTestLanguage.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaUnorderedGroupsTestLanguage.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaUnorderedGroupsTestLanguage.getANY_OTHERRule();
	} 
}
