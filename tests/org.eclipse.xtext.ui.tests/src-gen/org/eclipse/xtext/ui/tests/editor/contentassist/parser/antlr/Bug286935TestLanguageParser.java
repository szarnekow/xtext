/*
* generated by Xtext
*/
package org.eclipse.xtext.ui.tests.editor.contentassist.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.ui.tests.editor.contentassist.services.Bug286935TestLanguageGrammarAccess;

public class Bug286935TestLanguageParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private Bug286935TestLanguageGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.xtext.ui.tests.editor.contentassist.parser.antlr.internal.InternalBug286935TestLanguageParser createParser(XtextTokenStream stream) {
		return new org.eclipse.xtext.ui.tests.editor.contentassist.parser.antlr.internal.InternalBug286935TestLanguageParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "State";
	}
	
	public Bug286935TestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(Bug286935TestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
