package org.eclipse.xtext.serializer.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.contextFinderTest.AttributeExclusionTest;
import org.eclipse.xtext.serializer.contextFinderTest.ContextFinderTestPackage;
import org.eclipse.xtext.serializer.contextFinderTest.Model;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeChild1;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeChild2;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeRecursiveTest;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeRecursiveTest1;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeRecursiveTest2;
import org.eclipse.xtext.serializer.contextFinderTest.NestedTypeTest;
import org.eclipse.xtext.serializer.contextFinderTest.ParentRefTest1;
import org.eclipse.xtext.serializer.contextFinderTest.ParentRefTest2;
import org.eclipse.xtext.serializer.contextFinderTest.ParentRefTestChild;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.serializer.services.ContextFinderTestLanguageGrammarAccess;

@SuppressWarnings("restriction")
public class AbstractContextFinderTestLanguageSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected ContextFinderTestLanguageGrammarAccess grammarAccess;
	
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
		if(semanticObject.eClass().getEPackage() == ContextFinderTestPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ContextFinderTestPackage.ATTRIBUTE_EXCLUSION_TEST:
				if(context == grammarAccess.getAttributeExclusionTest1Rule()) {
					sequence_AttributeExclusionTest1_AttributeExclusionTest(context, (AttributeExclusionTest) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAttributeExclusionTest2Rule()) {
					sequence_AttributeExclusionTest2_AttributeExclusionTest(context, (AttributeExclusionTest) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model_Model(context, (Model) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getModel2Rule()) {
					sequence_Model2_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_CHILD1:
				if(context == grammarAccess.getNestedTypeChildRule() ||
				   context == grammarAccess.getNestedTypeChild1Rule()) {
					sequence_NestedTypeChild1_NestedTypeChild1(context, (NestedTypeChild1) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_CHILD2:
				if(context == grammarAccess.getNestedTypeChildRule() ||
				   context == grammarAccess.getNestedTypeChild2Rule()) {
					sequence_NestedTypeChild2_NestedTypeChild2(context, (NestedTypeChild2) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_RECURSIVE_TEST:
				if(context == grammarAccess.getNestedTypeRecursiveTest1Rule() ||
				   context == grammarAccess.getNestedTypeRecursiveTest1Access().getNestedTypeRecursiveTestLeftAction_2_0()) {
					sequence_NestedTypeRecursiveTest1_NestedTypeRecursiveTest(context, (NestedTypeRecursiveTest) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNestedTypeRecursiveTest2Rule() ||
				   context == grammarAccess.getNestedTypeRecursiveTest2Access().getNestedTypeRecursiveTestLeftAction_2_0()) {
					sequence_NestedTypeRecursiveTest2_NestedTypeRecursiveTest(context, (NestedTypeRecursiveTest) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_RECURSIVE_TEST1:
				if(context == grammarAccess.getNestedTypeRecursiveTest1Access().getNestedTypeRecursiveTestLeftAction_2_0()) {
					sequence_NestedTypeRecursiveTest1_NestedTypeRecursiveTest_2_0_NestedTypeRecursiveTest1(context, (NestedTypeRecursiveTest1) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_RECURSIVE_TEST2:
				if(context == grammarAccess.getNestedTypeRecursiveTest2Access().getNestedTypeRecursiveTestLeftAction_2_0()) {
					sequence_NestedTypeRecursiveTest2_NestedTypeRecursiveTest_2_0_NestedTypeRecursiveTest2(context, (NestedTypeRecursiveTest2) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.NESTED_TYPE_TEST:
				if(context == grammarAccess.getNestedTypeTest1Rule()) {
					sequence_NestedTypeTest1_NestedTypeTest(context, (NestedTypeTest) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNestedTypeTest2Rule()) {
					sequence_NestedTypeTest2_NestedTypeTest(context, (NestedTypeTest) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.PARENT_REF_TEST1:
				if(context == grammarAccess.getParentRefTest1Rule()) {
					sequence_ParentRefTest1_ParentRefTest1(context, (ParentRefTest1) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.PARENT_REF_TEST2:
				if(context == grammarAccess.getParentRefTest2Rule()) {
					sequence_ParentRefTest2_ParentRefTest2(context, (ParentRefTest2) semanticObject); 
					return; 
				}
				else break;
			case ContextFinderTestPackage.PARENT_REF_TEST_CHILD:
				if(context == grammarAccess.getParentRefTestChild1Rule()) {
					sequence_ParentRefTestChild1_ParentRefTestChild(context, (ParentRefTestChild) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getParentRefTestChild2Rule()) {
					sequence_ParentRefTestChild2_ParentRefTestChild(context, (ParentRefTestChild) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     attr1=ID
	 *
	 * Features:
	 *    attr1[1, 1]
	 */
	protected void sequence_AttributeExclusionTest1_AttributeExclusionTest(EObject context, AttributeExclusionTest semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     attr2=ID
	 *
	 * Features:
	 *    attr2[1, 1]
	 */
	protected void sequence_AttributeExclusionTest2_AttributeExclusionTest(EObject context, AttributeExclusionTest semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {Model}
	 *
	 * Features:
	 */
	protected void sequence_Model2_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         model=Model2 | 
	 *         attributeExclusion=AttributeExclusionTest1 | 
	 *         attributeExclusion=AttributeExclusionTest2 | 
	 *         nestedType=NestedTypeTest1 | 
	 *         nestedType=NestedTypeTest2 | 
	 *         nestedTypeRecursiveTest2=NestedTypeRecursiveTest1 | 
	 *         nestedTypeRecursiveTest2=NestedTypeRecursiveTest2 | 
	 *         parentRef=ParentRefTest1 | 
	 *         parentRef=ParentRefTest2
	 *     )
	 *
	 * Features:
	 *    model[0, 1]
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET parentRef
	 *         EXCLUDE_IF_SET parentRef
	 *    attributeExclusion[0, 2]
	 *         EXCLUDE_IF_SET model
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET parentRef
	 *         EXCLUDE_IF_SET parentRef
	 *    nestedType[0, 2]
	 *         EXCLUDE_IF_SET model
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET parentRef
	 *         EXCLUDE_IF_SET parentRef
	 *    nestedTypeRecursiveTest2[0, 2]
	 *         EXCLUDE_IF_SET model
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET parentRef
	 *         EXCLUDE_IF_SET parentRef
	 *    parentRef[0, 2]
	 *         EXCLUDE_IF_SET model
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET attributeExclusion
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedType
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 *         EXCLUDE_IF_SET nestedTypeRecursiveTest2
	 */
	protected void sequence_Model_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val=ID
	 *
	 * Features:
	 *    val[1, 1]
	 */
	protected void sequence_NestedTypeChild1_NestedTypeChild1(EObject context, NestedTypeChild1 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_CHILD__VAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_CHILD__VAL));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeChild1Access().getValIDTerminalRuleCall_1_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     val=ID
	 *
	 * Features:
	 *    val[1, 1]
	 */
	protected void sequence_NestedTypeChild2_NestedTypeChild2(EObject context, NestedTypeChild2 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_CHILD__VAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_CHILD__VAL));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeChild2Access().getValIDTerminalRuleCall_1_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     left=NestedTypeRecursiveTest1_NestedTypeRecursiveTest_2_0
	 *
	 * Features:
	 *    left[1, 1]
	 */
	protected void sequence_NestedTypeRecursiveTest1_NestedTypeRecursiveTest(EObject context, NestedTypeRecursiveTest semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_RECURSIVE_TEST__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_RECURSIVE_TEST__LEFT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeRecursiveTest1Access().getNestedTypeRecursiveTestLeftAction_2_0(), semanticObject.getLeft());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     {NestedTypeRecursiveTest1}
	 *
	 * Features:
	 */
	protected void sequence_NestedTypeRecursiveTest1_NestedTypeRecursiveTest_2_0_NestedTypeRecursiveTest1(EObject context, NestedTypeRecursiveTest1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     left=NestedTypeRecursiveTest2_NestedTypeRecursiveTest_2_0
	 *
	 * Features:
	 *    left[1, 1]
	 */
	protected void sequence_NestedTypeRecursiveTest2_NestedTypeRecursiveTest(EObject context, NestedTypeRecursiveTest semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_RECURSIVE_TEST__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_RECURSIVE_TEST__LEFT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeRecursiveTest2Access().getNestedTypeRecursiveTestLeftAction_2_0(), semanticObject.getLeft());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     {NestedTypeRecursiveTest2}
	 *
	 * Features:
	 */
	protected void sequence_NestedTypeRecursiveTest2_NestedTypeRecursiveTest_2_0_NestedTypeRecursiveTest2(EObject context, NestedTypeRecursiveTest2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     nested=NestedTypeChild1
	 *
	 * Features:
	 *    nested[1, 1]
	 */
	protected void sequence_NestedTypeTest1_NestedTypeTest(EObject context, NestedTypeTest semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_TEST__NESTED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_TEST__NESTED));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeTest1Access().getNestedNestedTypeChild1ParserRuleCall_0(), semanticObject.getNested());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     nested=NestedTypeChild2
	 *
	 * Features:
	 *    nested[1, 1]
	 */
	protected void sequence_NestedTypeTest2_NestedTypeTest(EObject context, NestedTypeTest semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_TEST__NESTED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.NESTED_TYPE_TEST__NESTED));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNestedTypeTest2Access().getNestedNestedTypeChild2ParserRuleCall_0(), semanticObject.getNested());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     child1=ParentRefTestChild1
	 *
	 * Features:
	 *    child1[1, 1]
	 */
	protected void sequence_ParentRefTest1_ParentRefTest1(EObject context, ParentRefTest1 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.PARENT_REF_TEST1__CHILD1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.PARENT_REF_TEST1__CHILD1));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParentRefTest1Access().getChild1ParentRefTestChild1ParserRuleCall_1_0(), semanticObject.getChild1());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     child2=ParentRefTestChild2
	 *
	 * Features:
	 *    child2[1, 1]
	 */
	protected void sequence_ParentRefTest2_ParentRefTest2(EObject context, ParentRefTest2 semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ContextFinderTestPackage.Literals.PARENT_REF_TEST2__CHILD2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ContextFinderTestPackage.Literals.PARENT_REF_TEST2__CHILD2));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParentRefTest2Access().getChild2ParentRefTestChild2ParserRuleCall_1_0(), semanticObject.getChild2());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (child=ParentRefTestChild1?)
	 *
	 * Features:
	 *    child[0, 1]
	 */
	protected void sequence_ParentRefTestChild1_ParentRefTestChild(EObject context, ParentRefTestChild semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (child=ParentRefTestChild2?)
	 *
	 * Features:
	 *    child[0, 1]
	 */
	protected void sequence_ParentRefTestChild2_ParentRefTestChild(EObject context, ParentRefTestChild semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}