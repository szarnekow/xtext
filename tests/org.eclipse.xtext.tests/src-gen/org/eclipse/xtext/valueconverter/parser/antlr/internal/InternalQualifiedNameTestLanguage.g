/*
* generated by Xtext
*/
grammar InternalQualifiedNameTestLanguage;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.xtext.valueconverter.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.xtext.valueconverter.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.valueconverter.services.QualifiedNameTestLanguageGrammarAccess;

}

@parser::members {

 	private QualifiedNameTestLanguageGrammarAccess grammarAccess;
 	
    public InternalQualifiedNameTestLanguageParser(TokenStream input, IAstFactory factory, QualifiedNameTestLanguageGrammarAccess grammarAccess) {
        this(input);
        this.factory = factory;
        registerRules(grammarAccess.getGrammar());
        this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Element";	
   	}
   	
   	@Override
   	protected QualifiedNameTestLanguageGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleElement
entryRuleElement returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getElementRule(), currentNode); }
	 iv_ruleElement=ruleElement 
	 { $current=$iv_ruleElement.current; } 
	 EOF 
;

// Rule Element
ruleElement returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	otherlv_0='keyword' 
    {
    	createLeafNode(otherlv_0, grammarAccess.getElementAccess().getKeywordKeyword_0(), null);
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getElementAccess().getQualifiedNameQualifiedNameParserRuleCall_1_0(), currentNode); 
	    }
		lv_qualifiedName_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getElementRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"qualifiedName",
	        		lv_qualifiedName_1_0, 
	        		"QualifiedName", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
))
;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getQualifiedNameRule(), currentNode); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
	    lastConsumedNode = currentNode;
    }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    createLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0(), null); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        createLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0(), null); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    createLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1(), null); 
    }
)*(
	kw='.' 
    {
        $current.merge(kw);
        createLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_2_0(), null); 
    }

	kw='*' 
    {
        $current.merge(kw);
        createLeafNode(kw, grammarAccess.getQualifiedNameAccess().getAsteriskKeyword_2_1(), null); 
    }
)?)
    ;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

