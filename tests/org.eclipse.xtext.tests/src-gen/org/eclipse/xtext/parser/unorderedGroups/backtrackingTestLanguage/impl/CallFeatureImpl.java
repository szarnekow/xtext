/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.BacktrackingTestLanguagePackage;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.CallFeature;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.Expression;
import org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.ParameterList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.impl.CallFeatureImpl#getFuncExpr <em>Func Expr</em>}</li>
 *   <li>{@link org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.impl.CallFeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.xtext.parser.unorderedGroups.backtrackingTestLanguage.impl.CallFeatureImpl#getParameterList <em>Parameter List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallFeatureImpl extends ExpressionImpl implements CallFeature
{
  /**
   * The cached value of the '{@link #getFuncExpr() <em>Func Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFuncExpr()
   * @generated
   * @ordered
   */
  protected Expression funcExpr;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameterList() <em>Parameter List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameterList()
   * @generated
   * @ordered
   */
  protected ParameterList parameterList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CallFeatureImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return BacktrackingTestLanguagePackage.Literals.CALL_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getFuncExpr()
  {
    return funcExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFuncExpr(Expression newFuncExpr, NotificationChain msgs)
  {
    Expression oldFuncExpr = funcExpr;
    funcExpr = newFuncExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR, oldFuncExpr, newFuncExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFuncExpr(Expression newFuncExpr)
  {
    if (newFuncExpr != funcExpr)
    {
      NotificationChain msgs = null;
      if (funcExpr != null)
        msgs = ((InternalEObject)funcExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR, null, msgs);
      if (newFuncExpr != null)
        msgs = ((InternalEObject)newFuncExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR, null, msgs);
      msgs = basicSetFuncExpr(newFuncExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR, newFuncExpr, newFuncExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BacktrackingTestLanguagePackage.CALL_FEATURE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterList getParameterList()
  {
    return parameterList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameterList(ParameterList newParameterList, NotificationChain msgs)
  {
    ParameterList oldParameterList = parameterList;
    parameterList = newParameterList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST, oldParameterList, newParameterList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameterList(ParameterList newParameterList)
  {
    if (newParameterList != parameterList)
    {
      NotificationChain msgs = null;
      if (parameterList != null)
        msgs = ((InternalEObject)parameterList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST, null, msgs);
      if (newParameterList != null)
        msgs = ((InternalEObject)newParameterList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST, null, msgs);
      msgs = basicSetParameterList(newParameterList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST, newParameterList, newParameterList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR:
        return basicSetFuncExpr(null, msgs);
      case BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST:
        return basicSetParameterList(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR:
        return getFuncExpr();
      case BacktrackingTestLanguagePackage.CALL_FEATURE__NAME:
        return getName();
      case BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST:
        return getParameterList();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR:
        setFuncExpr((Expression)newValue);
        return;
      case BacktrackingTestLanguagePackage.CALL_FEATURE__NAME:
        setName((String)newValue);
        return;
      case BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST:
        setParameterList((ParameterList)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR:
        setFuncExpr((Expression)null);
        return;
      case BacktrackingTestLanguagePackage.CALL_FEATURE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST:
        setParameterList((ParameterList)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case BacktrackingTestLanguagePackage.CALL_FEATURE__FUNC_EXPR:
        return funcExpr != null;
      case BacktrackingTestLanguagePackage.CALL_FEATURE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BacktrackingTestLanguagePackage.CALL_FEATURE__PARAMETER_LIST:
        return parameterList != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //CallFeatureImpl