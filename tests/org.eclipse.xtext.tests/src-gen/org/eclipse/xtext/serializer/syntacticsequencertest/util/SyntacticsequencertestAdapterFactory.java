/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.serializer.syntacticsequencertest.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.serializer.syntacticsequencertest.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.xtext.serializer.syntacticsequencertest.SyntacticsequencertestPackage
 * @generated
 */
public class SyntacticsequencertestAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SyntacticsequencertestPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SyntacticsequencertestAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = SyntacticsequencertestPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SyntacticsequencertestSwitch<Adapter> modelSwitch =
    new SyntacticsequencertestSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseMandatoryKeywords(MandatoryKeywords object)
      {
        return createMandatoryKeywordsAdapter();
      }
      @Override
      public Adapter caseExp0(Exp0 object)
      {
        return createExp0Adapter();
      }
      @Override
      public Adapter caseExp1(Exp1 object)
      {
        return createExp1Adapter();
      }
      @Override
      public Adapter caseExp2(Exp2 object)
      {
        return createExp2Adapter();
      }
      @Override
      public Adapter caseAdd0(Add0 object)
      {
        return createAdd0Adapter();
      }
      @Override
      public Adapter caseVal0(Val0 object)
      {
        return createVal0Adapter();
      }
      @Override
      public Adapter caseAdd1(Add1 object)
      {
        return createAdd1Adapter();
      }
      @Override
      public Adapter caseVal1(Val1 object)
      {
        return createVal1Adapter();
      }
      @Override
      public Adapter caseAdd2(Add2 object)
      {
        return createAdd2Adapter();
      }
      @Override
      public Adapter caseMult2(Mult2 object)
      {
        return createMult2Adapter();
      }
      @Override
      public Adapter caseVal2(Val2 object)
      {
        return createVal2Adapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.MandatoryKeywords <em>Mandatory Keywords</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.MandatoryKeywords
   * @generated
   */
  public Adapter createMandatoryKeywordsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Exp0 <em>Exp0</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Exp0
   * @generated
   */
  public Adapter createExp0Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Exp1 <em>Exp1</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Exp1
   * @generated
   */
  public Adapter createExp1Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Exp2 <em>Exp2</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Exp2
   * @generated
   */
  public Adapter createExp2Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Add0 <em>Add0</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Add0
   * @generated
   */
  public Adapter createAdd0Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Val0 <em>Val0</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Val0
   * @generated
   */
  public Adapter createVal0Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Add1 <em>Add1</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Add1
   * @generated
   */
  public Adapter createAdd1Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Val1 <em>Val1</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Val1
   * @generated
   */
  public Adapter createVal1Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Add2 <em>Add2</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Add2
   * @generated
   */
  public Adapter createAdd2Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Mult2 <em>Mult2</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Mult2
   * @generated
   */
  public Adapter createMult2Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.xtext.serializer.syntacticsequencertest.Val2 <em>Val2</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.xtext.serializer.syntacticsequencertest.Val2
   * @generated
   */
  public Adapter createVal2Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //SyntacticsequencertestAdapterFactory