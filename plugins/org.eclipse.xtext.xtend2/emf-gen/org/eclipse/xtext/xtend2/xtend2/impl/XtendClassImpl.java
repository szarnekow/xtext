/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.xtend2.xtend2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xtend2.xtend2.Xtend2Package;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.eclipse.xtext.xtend2.xtend2.XtendClassSuperCallReferable;
import org.eclipse.xtext.xtend2.xtend2.XtendMember;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Xtend Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getSuperCallReferable <em>Super Call Referable</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendClassImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XtendClassImpl extends XtendAnnotationTargetImpl implements XtendClass
{
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
	 * The cached value of the '{@link #getExtends() <em>Extends</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtends()
	 * @generated
	 * @ordered
	 */
	protected JvmTypeReference extends_;

	/**
	 * The cached value of the '{@link #getImplements() <em>Implements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplements()
	 * @generated
	 * @ordered
	 */
	protected EList<JvmTypeReference> implements_;

	/**
	 * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<JvmTypeReference> superTypes;

	/**
	 * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<XtendMember> members;

	/**
	 * The cached value of the '{@link #getSuperCallReferable() <em>Super Call Referable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperCallReferable()
	 * @generated
	 * @ordered
	 */
	protected XtendClassSuperCallReferable superCallReferable;

	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<JvmTypeParameter> typeParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XtendClassImpl()
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
		return Xtend2Package.Literals.XTEND_CLASS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_CLASS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JvmTypeReference getExtends()
	{
		return extends_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtends(JvmTypeReference newExtends, NotificationChain msgs)
	{
		JvmTypeReference oldExtends = extends_;
		extends_ = newExtends;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_CLASS__EXTENDS, oldExtends, newExtends);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtends(JvmTypeReference newExtends)
	{
		if (newExtends != extends_)
		{
			NotificationChain msgs = null;
			if (extends_ != null)
				msgs = ((InternalEObject)extends_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_CLASS__EXTENDS, null, msgs);
			if (newExtends != null)
				msgs = ((InternalEObject)newExtends).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_CLASS__EXTENDS, null, msgs);
			msgs = basicSetExtends(newExtends, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_CLASS__EXTENDS, newExtends, newExtends));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmTypeReference> getImplements()
	{
		if (implements_ == null)
		{
			implements_ = new EObjectContainmentEList<JvmTypeReference>(JvmTypeReference.class, this, Xtend2Package.XTEND_CLASS__IMPLEMENTS);
		}
		return implements_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmTypeReference> getSuperTypes()
	{
		if (superTypes == null)
		{
			superTypes = new EObjectResolvingEList<JvmTypeReference>(JvmTypeReference.class, this, Xtend2Package.XTEND_CLASS__SUPER_TYPES);
		}
		return superTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<XtendMember> getMembers()
	{
		if (members == null)
		{
			members = new EObjectContainmentEList<XtendMember>(XtendMember.class, this, Xtend2Package.XTEND_CLASS__MEMBERS);
		}
		return members;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XtendClassSuperCallReferable getSuperCallReferable()
	{
		return superCallReferable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperCallReferable(XtendClassSuperCallReferable newSuperCallReferable, NotificationChain msgs)
	{
		XtendClassSuperCallReferable oldSuperCallReferable = superCallReferable;
		superCallReferable = newSuperCallReferable;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE, oldSuperCallReferable, newSuperCallReferable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperCallReferable(XtendClassSuperCallReferable newSuperCallReferable)
	{
		if (newSuperCallReferable != superCallReferable)
		{
			NotificationChain msgs = null;
			if (superCallReferable != null)
				msgs = ((InternalEObject)superCallReferable).eInverseRemove(this, Xtend2Package.XTEND_CLASS_SUPER_CALL_REFERABLE__XTEND_CLASS, XtendClassSuperCallReferable.class, msgs);
			if (newSuperCallReferable != null)
				msgs = ((InternalEObject)newSuperCallReferable).eInverseAdd(this, Xtend2Package.XTEND_CLASS_SUPER_CALL_REFERABLE__XTEND_CLASS, XtendClassSuperCallReferable.class, msgs);
			msgs = basicSetSuperCallReferable(newSuperCallReferable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE, newSuperCallReferable, newSuperCallReferable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmTypeParameter> getTypeParameters()
	{
		if (typeParameters == null)
		{
			typeParameters = new EObjectContainmentEList<JvmTypeParameter>(JvmTypeParameter.class, this, Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS);
		}
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSimpleName()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				if (superCallReferable != null)
					msgs = ((InternalEObject)superCallReferable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE, null, msgs);
				return basicSetSuperCallReferable((XtendClassSuperCallReferable)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case Xtend2Package.XTEND_CLASS__EXTENDS:
				return basicSetExtends(null, msgs);
			case Xtend2Package.XTEND_CLASS__IMPLEMENTS:
				return ((InternalEList<?>)getImplements()).basicRemove(otherEnd, msgs);
			case Xtend2Package.XTEND_CLASS__MEMBERS:
				return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				return basicSetSuperCallReferable(null, msgs);
			case Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS:
				return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
			case Xtend2Package.XTEND_CLASS__NAME:
				return getName();
			case Xtend2Package.XTEND_CLASS__EXTENDS:
				return getExtends();
			case Xtend2Package.XTEND_CLASS__IMPLEMENTS:
				return getImplements();
			case Xtend2Package.XTEND_CLASS__SUPER_TYPES:
				return getSuperTypes();
			case Xtend2Package.XTEND_CLASS__MEMBERS:
				return getMembers();
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				return getSuperCallReferable();
			case Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS:
				return getTypeParameters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_CLASS__NAME:
				setName((String)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__EXTENDS:
				setExtends((JvmTypeReference)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__IMPLEMENTS:
				getImplements().clear();
				getImplements().addAll((Collection<? extends JvmTypeReference>)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__SUPER_TYPES:
				getSuperTypes().clear();
				getSuperTypes().addAll((Collection<? extends JvmTypeReference>)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends XtendMember>)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				setSuperCallReferable((XtendClassSuperCallReferable)newValue);
				return;
			case Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS:
				getTypeParameters().clear();
				getTypeParameters().addAll((Collection<? extends JvmTypeParameter>)newValue);
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
			case Xtend2Package.XTEND_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Xtend2Package.XTEND_CLASS__EXTENDS:
				setExtends((JvmTypeReference)null);
				return;
			case Xtend2Package.XTEND_CLASS__IMPLEMENTS:
				getImplements().clear();
				return;
			case Xtend2Package.XTEND_CLASS__SUPER_TYPES:
				getSuperTypes().clear();
				return;
			case Xtend2Package.XTEND_CLASS__MEMBERS:
				getMembers().clear();
				return;
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				setSuperCallReferable((XtendClassSuperCallReferable)null);
				return;
			case Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS:
				getTypeParameters().clear();
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
			case Xtend2Package.XTEND_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Xtend2Package.XTEND_CLASS__EXTENDS:
				return extends_ != null;
			case Xtend2Package.XTEND_CLASS__IMPLEMENTS:
				return implements_ != null && !implements_.isEmpty();
			case Xtend2Package.XTEND_CLASS__SUPER_TYPES:
				return superTypes != null && !superTypes.isEmpty();
			case Xtend2Package.XTEND_CLASS__MEMBERS:
				return members != null && !members.isEmpty();
			case Xtend2Package.XTEND_CLASS__SUPER_CALL_REFERABLE:
				return superCallReferable != null;
			case Xtend2Package.XTEND_CLASS__TYPE_PARAMETERS:
				return typeParameters != null && !typeParameters.isEmpty();
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

} //XtendClassImpl
