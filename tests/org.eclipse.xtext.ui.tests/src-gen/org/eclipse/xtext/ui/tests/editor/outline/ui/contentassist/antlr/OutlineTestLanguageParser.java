/*
* generated by Xtext
*/
package org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.xtext.ui.tests.editor.outline.services.OutlineTestLanguageGrammarAccess;

public class OutlineTestLanguageParser extends AbstractContentAssistParser {
	
	@Inject
	private OutlineTestLanguageGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr.internal.InternalOutlineTestLanguageParser createParser() {
		org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr.internal.InternalOutlineTestLanguageParser result = new org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr.internal.InternalOutlineTestLanguageParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getElementAccess().getGroup(), "rule__Element__Group__0");
					put(grammarAccess.getElementAccess().getGroup_1(), "rule__Element__Group_1__0");
					put(grammarAccess.getModelAccess().getElementsAssignment(), "rule__Model__ElementsAssignment");
					put(grammarAccess.getElementAccess().getNameAssignment_0(), "rule__Element__NameAssignment_0");
					put(grammarAccess.getElementAccess().getXrefsAssignment_1_1(), "rule__Element__XrefsAssignment_1_1");
					put(grammarAccess.getElementAccess().getChildrenAssignment_3(), "rule__Element__ChildrenAssignment_3");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr.internal.InternalOutlineTestLanguageParser typedParser = (org.eclipse.xtext.ui.tests.editor.outline.ui.contentassist.antlr.internal.InternalOutlineTestLanguageParser) parser;
			typedParser.entryRuleModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public OutlineTestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(OutlineTestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}