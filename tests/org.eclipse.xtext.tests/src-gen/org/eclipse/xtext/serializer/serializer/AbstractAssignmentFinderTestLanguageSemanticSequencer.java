package org.eclipse.xtext.serializer.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.assignmentFinderTest.AssignmentFinderTestPackage;
import org.eclipse.xtext.serializer.assignmentFinderTest.ContainmentRef;
import org.eclipse.xtext.serializer.assignmentFinderTest.ContainmentRefN;
import org.eclipse.xtext.serializer.assignmentFinderTest.CrossRef;
import org.eclipse.xtext.serializer.assignmentFinderTest.EnumBool;
import org.eclipse.xtext.serializer.assignmentFinderTest.EnumVal;
import org.eclipse.xtext.serializer.assignmentFinderTest.KeywordBool;
import org.eclipse.xtext.serializer.assignmentFinderTest.KeywordVal;
import org.eclipse.xtext.serializer.assignmentFinderTest.MixedBool;
import org.eclipse.xtext.serializer.assignmentFinderTest.Model;
import org.eclipse.xtext.serializer.assignmentFinderTest.TerminalBool;
import org.eclipse.xtext.serializer.assignmentFinderTest.TerminalVal;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.services.AssignmentFinderTestLanguageGrammarAccess;

@SuppressWarnings("restriction")
public class AbstractAssignmentFinderTestLanguageSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected AssignmentFinderTestLanguageGrammarAccess grammarAccess;
	
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
		if(semanticObject.eClass().getEPackage() == AssignmentFinderTestPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case AssignmentFinderTestPackage.CONTAINMENT_REF:
				if(context == grammarAccess.getContainmentRefRule()) {
					sequence_ContainmentRef_ContainmentRef(context, (ContainmentRef) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.CONTAINMENT_REF_N:
				if(context == grammarAccess.getContainmentRef2Rule()) {
					sequence_ContainmentRef2_ContainmentRefN(context, (ContainmentRefN) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getContainmentRef1Rule()) {
					sequence_ContainmentRef1_ContainmentRefN(context, (ContainmentRefN) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.CROSS_REF:
				if(context == grammarAccess.getCrossRefRule()) {
					sequence_CrossRef_CrossRef(context, (CrossRef) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.ENUM_BOOL:
				if(context == grammarAccess.getEnumBoolRule()) {
					sequence_EnumBool_EnumBool(context, (EnumBool) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.ENUM_VAL:
				if(context == grammarAccess.getEnumValRule()) {
					sequence_EnumVal_EnumVal(context, (EnumVal) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.KEYWORD_BOOL:
				if(context == grammarAccess.getKeywordBoolRule()) {
					sequence_KeywordBool_KeywordBool(context, (KeywordBool) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.KEYWORD_VAL:
				if(context == grammarAccess.getKeywordValRule()) {
					sequence_KeywordVal_KeywordVal(context, (KeywordVal) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.MIXED_BOOL:
				if(context == grammarAccess.getMixedBoolRule()) {
					sequence_MixedBool_MixedBool(context, (MixedBool) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.TERMINAL_BOOL:
				if(context == grammarAccess.getTerminalBoolRule()) {
					sequence_TerminalBool_TerminalBool(context, (TerminalBool) semanticObject); 
					return; 
				}
				else break;
			case AssignmentFinderTestPackage.TERMINAL_VAL:
				if(context == grammarAccess.getTerminalValRule()) {
					sequence_TerminalVal_TerminalVal(context, (TerminalVal) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     val1=ID
	 *
	 * Features:
	 *    val1[1, 1]
	 */
	protected void sequence_ContainmentRef1_ContainmentRefN(EObject context, ContainmentRefN semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val2=ID
	 *
	 * Features:
	 *    val2[1, 1]
	 */
	protected void sequence_ContainmentRef2_ContainmentRefN(EObject context, ContainmentRefN semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ctx=ContainmentRef1 | ctx=ContainmentRef2)
	 *
	 * Features:
	 *    ctx[0, 2]
	 */
	protected void sequence_ContainmentRef_ContainmentRef(EObject context, ContainmentRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((name=Terminal1 | name=Terminal2) (crossRef=[CrossRef|Terminal1] | crossRef=[CrossRef|Terminal2]))
	 *
	 * Features:
	 *    name[0, 2]
	 *    crossRef[0, 2]
	 */
	protected void sequence_CrossRef_CrossRef(EObject context, CrossRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (en?=Enum1 | en?=Enum2)
	 *
	 * Features:
	 *    en[0, 2]
	 */
	protected void sequence_EnumBool_EnumBool(EObject context, EnumBool semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (en=Enum1 | en=Enum2)
	 *
	 * Features:
	 *    en[0, 2]
	 */
	protected void sequence_EnumVal_EnumVal(EObject context, EnumVal semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (kw?='kw1' | kw?='kw2')
	 *
	 * Features:
	 *    kw[0, 2]
	 */
	protected void sequence_KeywordBool_KeywordBool(EObject context, KeywordBool semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (kw='kw1' | kw='kw2')
	 *
	 * Features:
	 *    kw[0, 2]
	 */
	protected void sequence_KeywordVal_KeywordVal(EObject context, KeywordVal semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (val?='kw1' | val=Boolean)
	 *
	 * Features:
	 *    val[0, 2]
	 */
	protected void sequence_MixedBool_MixedBool(EObject context, MixedBool semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         keywordVal=KeywordVal | 
	 *         terminalVal=TerminalVal | 
	 *         enumVal=EnumVal | 
	 *         keywordBool=KeywordBool | 
	 *         terminalBool=TerminalBool | 
	 *         enumBool=EnumBool | 
	 *         mixedBool=MixedBool | 
	 *         containmentRef=ContainmentRef | 
	 *         crossRef=CrossRef
	 *     )
	 *
	 * Features:
	 *    keywordVal[0, 1]
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    terminalVal[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    enumVal[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    keywordBool[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    terminalBool[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    enumBool[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    mixedBool[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET containmentRef
	 *         EXCLUDE_IF_SET crossRef
	 *    containmentRef[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET crossRef
	 *    crossRef[0, 1]
	 *         EXCLUDE_IF_SET keywordVal
	 *         EXCLUDE_IF_SET terminalVal
	 *         EXCLUDE_IF_SET enumVal
	 *         EXCLUDE_IF_SET keywordBool
	 *         EXCLUDE_IF_SET terminalBool
	 *         EXCLUDE_IF_SET enumBool
	 *         EXCLUDE_IF_SET mixedBool
	 *         EXCLUDE_IF_SET containmentRef
	 */
	protected void sequence_Model_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (term?=Terminal1 | term?=Terminal2 | term?='%foo')
	 *
	 * Features:
	 *    term[0, 3]
	 */
	protected void sequence_TerminalBool_TerminalBool(EObject context, TerminalBool semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (term=Terminal1 | term=Terminal2 | term='%foo')
	 *
	 * Features:
	 *    term[0, 3]
	 */
	protected void sequence_TerminalVal_TerminalVal(EObject context, TerminalVal semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}