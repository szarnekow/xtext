package org.eclipse.xtext.resource.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.services.EObjectAtOffsetTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("restriction")
public class AbstractEObjectAtOffsetTestLanguageSyntacticSequencer extends AbstractSyntacticSequencer {

	protected EObjectAtOffsetTestLanguageGrammarAccess grammarAccess;
	protected AbstractElementAlias match_AbstractBar_INTTerminalRuleCall_1_q;
	protected AbstractElementAlias match_AbstractBar_ZonkKeyword_0_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (EObjectAtOffsetTestLanguageGrammarAccess) access;
		match_AbstractBar_INTTerminalRuleCall_1_q = new TokenAlias(true, false, grammarAccess.getAbstractBarAccess().getINTTerminalRuleCall_1());
		match_AbstractBar_ZonkKeyword_0_q = new TokenAlias(true, false, grammarAccess.getAbstractBarAccess().getZonkKeyword_0());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getINTRule())
			return getINTToken(semanticObject, ruleCall, node);
		return "";
	}
	
	protected String getINTToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_AbstractBar_INTTerminalRuleCall_1_q.equals(syntax))
				emit_AbstractBar_INTTerminalRuleCall_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AbstractBar_ZonkKeyword_0_q.equals(syntax))
				emit_AbstractBar_ZonkKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     INT?
	 */
	protected void emit_AbstractBar_INTTerminalRuleCall_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'zonk'?
	 */
	protected void emit_AbstractBar_ZonkKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}