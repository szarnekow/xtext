package org.eclipse.xtext.testlanguages.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.testlanguages.lookaheadLang.Entry;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead0;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead1;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead2;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead3;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead4;
import org.eclipse.xtext.testlanguages.lookaheadLang.LookaheadLangPackage;
import org.eclipse.xtext.testlanguages.services.LookaheadTestLanguageGrammarAccess;

@SuppressWarnings("restriction")
public class AbstractLookaheadTestLanguageSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected LookaheadTestLanguageGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
	}
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == LookaheadLangPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case LookaheadLangPackage.ENTRY:
				if(context == grammarAccess.getEntryRule()) {
					sequence_Entry(context, (Entry) semanticObject); 
					return; 
				}
				else break;
			case LookaheadLangPackage.LOOK_AHEAD0:
				if(context == grammarAccess.getAltsRule() ||
				   context == grammarAccess.getLookAhead0Rule()) {
					sequence_LookAhead0(context, (LookAhead0) semanticObject); 
					return; 
				}
				else break;
			case LookaheadLangPackage.LOOK_AHEAD1:
				if(context == grammarAccess.getAltsRule() ||
				   context == grammarAccess.getLookAhead1Rule()) {
					sequence_LookAhead1(context, (LookAhead1) semanticObject); 
					return; 
				}
				else break;
			case LookaheadLangPackage.LOOK_AHEAD2:
				if(context == grammarAccess.getLookAhead2Rule()) {
					sequence_LookAhead2(context, (LookAhead2) semanticObject); 
					return; 
				}
				else break;
			case LookaheadLangPackage.LOOK_AHEAD3:
				if(context == grammarAccess.getAltsRule() ||
				   context == grammarAccess.getLookAhead3Rule()) {
					sequence_LookAhead3(context, (LookAhead3) semanticObject); 
					return; 
				}
				else break;
			case LookaheadLangPackage.LOOK_AHEAD4:
				if(context == grammarAccess.getLookAhead4Rule()) {
					sequence_LookAhead4(context, (LookAhead4) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     contents+=Alts*
	 */
	protected void sequence_Entry(EObject context, Entry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     x='a'
	 */
	protected void sequence_LookAhead0(EObject context, LookAhead0 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.ALTS__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.ALTS__X));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLookAhead0Access().getXAKeyword_1_0(), semanticObject.getX());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (y=LookAhead2 x='b' z='d')
	 */
	protected void sequence_LookAhead1(EObject context, LookAhead1 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.ALTS__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.ALTS__X));
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD1__Y) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD1__Y));
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD1__Z) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD1__Z));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLookAhead1Access().getYLookAhead2ParserRuleCall_1_0(), semanticObject.getY());
		feeder.accept(grammarAccess.getLookAhead1Access().getXBKeyword_2_0(), semanticObject.getX());
		feeder.accept(grammarAccess.getLookAhead1Access().getZDKeyword_3_0(), semanticObject.getZ());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (z='foo' | z='bar')
	 */
	protected void sequence_LookAhead2(EObject context, LookAhead2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (x='b' z=LookAhead4)
	 */
	protected void sequence_LookAhead3(EObject context, LookAhead3 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.ALTS__X) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.ALTS__X));
			if(transientValues.isValueTransient(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD3__Z) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LookaheadLangPackage.Literals.LOOK_AHEAD3__Z));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLookAhead3Access().getXBKeyword_2_0(), semanticObject.getX());
		feeder.accept(grammarAccess.getLookAhead3Access().getZLookAhead4ParserRuleCall_3_0(), semanticObject.getZ());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (x='c' | x='d')
	 */
	protected void sequence_LookAhead4(EObject context, LookAhead4 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
