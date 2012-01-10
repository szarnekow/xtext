package org.eclipse.xtext.resource.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.locationprovidertest.Bus;
import org.eclipse.xtext.resource.locationprovidertest.Data;
import org.eclipse.xtext.resource.locationprovidertest.Element;
import org.eclipse.xtext.resource.locationprovidertest.LocationprovidertestPackage;
import org.eclipse.xtext.resource.locationprovidertest.Mode;
import org.eclipse.xtext.resource.locationprovidertest.Model;
import org.eclipse.xtext.resource.locationprovidertest.Port;
import org.eclipse.xtext.resource.locationprovidertest.Processor;
import org.eclipse.xtext.resource.locationprovidertest.Transition;
import org.eclipse.xtext.resource.services.LocationProviderTestLanguageGrammarAccess;
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

@SuppressWarnings("restriction")
public class AbstractLocationProviderTestLanguageSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected LocationProviderTestLanguageGrammarAccess grammarAccess;
	
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
		if(semanticObject.eClass().getEPackage() == LocationprovidertestPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case LocationprovidertestPackage.BUS:
				if(context == grammarAccess.getBusRule()) {
					sequence_Bus(context, (Bus) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getComponentRule()) {
					sequence_Component(context, (Bus) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.DATA:
				if(context == grammarAccess.getDataRule()) {
					sequence_Data(context, (Data) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.ELEMENT:
				if(context == grammarAccess.getElementRule()) {
					sequence_Element(context, (Element) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.MODE:
				if(context == grammarAccess.getModeRule()) {
					sequence_Mode(context, (Mode) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.PORT:
				if(context == grammarAccess.getPortRule()) {
					sequence_Port(context, (Port) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.PROCESSOR:
				if(context == grammarAccess.getComponentRule()) {
					sequence_Component(context, (Processor) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getProcessorRule()) {
					sequence_Processor(context, (Processor) semanticObject); 
					return; 
				}
				else break;
			case LocationprovidertestPackage.TRANSITION:
				if(context == grammarAccess.getTransitionRule()) {
					sequence_Transition(context, (Transition) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (name=ID port+=Port*)
	 */
	protected void sequence_Bus(EObject context, Bus semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID port+=Port* (mode+=Mode | transition+=Transition)*)
	 */
	protected void sequence_Component(EObject context, Bus semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID data+=Data* (mode+=Mode | transition+=Transition)*)
	 */
	protected void sequence_Component(EObject context, Processor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Data(EObject context, Data semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.DATA__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.DATA__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDataAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID singleref=[Element|ID]? multirefs+=[Element|ID]*)
	 */
	protected void sequence_Element(EObject context, Element semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Mode(EObject context, Mode semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.MODE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.MODE__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getModeAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (elements+=Element* components+=Component*)
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Port(EObject context, Port semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.PORT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.PORT__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPortAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID data+=Data*)
	 */
	protected void sequence_Processor(EObject context, Processor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID source=[Mode|ID] destination=[Mode|ID])
	 */
	protected void sequence_Transition(EObject context, Transition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__NAME));
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__SOURCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__SOURCE));
			if(transientValues.isValueTransient(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__DESTINATION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, LocationprovidertestPackage.Literals.TRANSITION__DESTINATION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTransitionAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getTransitionAccess().getSourceModeIDTerminalRuleCall_2_0_1(), semanticObject.getSource());
		feeder.accept(grammarAccess.getTransitionAccess().getDestinationModeIDTerminalRuleCall_4_0_1(), semanticObject.getDestination());
		feeder.finish();
	}
}
