/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.common.types.impl;

import java.lang.Iterable;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationTarget;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jvm Declared Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getDeclaringType <em>Declaring Type</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.xtext.common.types.impl.JvmDeclaredTypeImpl#isFinal <em>Final</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JvmDeclaredTypeImpl extends JvmComponentTypeImpl implements JvmDeclaredType
{
	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<JvmAnnotationReference> annotations;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final JvmVisibility VISIBILITY_EDEFAULT = JvmVisibility.DEFAULT;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected JvmVisibility visibility = VISIBILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFullyQualifiedName() <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullyQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULLY_QUALIFIED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullyQualifiedName() <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullyQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected String fullyQualifiedName = FULLY_QUALIFIED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' containment reference list.
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
	protected EList<JvmMember> members;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JvmDeclaredTypeImpl()
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
		return TypesPackage.Literals.JVM_DECLARED_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmAnnotationReference> getAnnotations()
	{
		if (annotations == null)
		{
			annotations = new EObjectContainmentWithInverseEList<JvmAnnotationReference>(JvmAnnotationReference.class, this, TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS, TypesPackage.JVM_ANNOTATION_REFERENCE__TARGET);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JvmDeclaredType getDeclaringType()
	{
		if (eContainerFeatureID() != TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE) return null;
		return (JvmDeclaredType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaringType(JvmDeclaredType newDeclaringType, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newDeclaringType, TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaringType(JvmDeclaredType newDeclaringType)
	{
		if (newDeclaringType != eInternalContainer() || (eContainerFeatureID() != TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE && newDeclaringType != null))
		{
			if (EcoreUtil.isAncestor(this, newDeclaringType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeclaringType != null)
				msgs = ((InternalEObject)newDeclaringType).eInverseAdd(this, TypesPackage.JVM_DECLARED_TYPE__MEMBERS, JvmDeclaredType.class, msgs);
			msgs = basicSetDeclaringType(newDeclaringType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE, newDeclaringType, newDeclaringType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JvmVisibility getVisibility()
	{
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(JvmVisibility newVisibility)
	{
		JvmVisibility oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullyQualifiedName()
	{
		return fullyQualifiedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullyQualifiedName(String newFullyQualifiedName)
	{
		String oldFullyQualifiedName = fullyQualifiedName;
		fullyQualifiedName = newFullyQualifiedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME, oldFullyQualifiedName, fullyQualifiedName));
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
			superTypes = new EObjectContainmentEList<JvmTypeReference>(JvmTypeReference.class, this, TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES);
		}
		return superTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmMember> getMembers()
	{
		if (members == null)
		{
			members = new EObjectContainmentWithInverseEList<JvmMember>(JvmMember.class, this, TypesPackage.JVM_DECLARED_TYPE__MEMBERS, TypesPackage.JVM_MEMBER__DECLARING_TYPE);
		}
		return members;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract()
	{
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract)
	{
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic()
	{
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic)
	{
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal()
	{
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal)
	{
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.JVM_DECLARED_TYPE__FINAL, oldFinal, final_));
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
	public Iterable<JvmOperation> getDeclaredOperations()
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
	public Iterable<JvmField> getDeclaredFields()
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotations()).basicAdd(otherEnd, msgs);
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDeclaringType((JvmDeclaredType)otherEnd, msgs);
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMembers()).basicAdd(otherEnd, msgs);
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
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				return basicSetDeclaringType(null, msgs);
			case TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES:
				return ((InternalEList<?>)getSuperTypes()).basicRemove(otherEnd, msgs);
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				return eInternalContainer().eInverseRemove(this, TypesPackage.JVM_DECLARED_TYPE__MEMBERS, JvmDeclaredType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				return getAnnotations();
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				return getDeclaringType();
			case TypesPackage.JVM_DECLARED_TYPE__VISIBILITY:
				return getVisibility();
			case TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME:
				return getFullyQualifiedName();
			case TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES:
				return getSuperTypes();
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				return getMembers();
			case TypesPackage.JVM_DECLARED_TYPE__ABSTRACT:
				return isAbstract();
			case TypesPackage.JVM_DECLARED_TYPE__STATIC:
				return isStatic();
			case TypesPackage.JVM_DECLARED_TYPE__FINAL:
				return isFinal();
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
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends JvmAnnotationReference>)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				setDeclaringType((JvmDeclaredType)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__VISIBILITY:
				setVisibility((JvmVisibility)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName((String)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES:
				getSuperTypes().clear();
				getSuperTypes().addAll((Collection<? extends JvmTypeReference>)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends JvmMember>)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__STATIC:
				setStatic((Boolean)newValue);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__FINAL:
				setFinal((Boolean)newValue);
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
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				setDeclaringType((JvmDeclaredType)null);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME:
				setFullyQualifiedName(FULLY_QUALIFIED_NAME_EDEFAULT);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES:
				getSuperTypes().clear();
				return;
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				getMembers().clear();
				return;
			case TypesPackage.JVM_DECLARED_TYPE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case TypesPackage.JVM_DECLARED_TYPE__FINAL:
				setFinal(FINAL_EDEFAULT);
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
			case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE:
				return getDeclaringType() != null;
			case TypesPackage.JVM_DECLARED_TYPE__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME:
				return FULLY_QUALIFIED_NAME_EDEFAULT == null ? fullyQualifiedName != null : !FULLY_QUALIFIED_NAME_EDEFAULT.equals(fullyQualifiedName);
			case TypesPackage.JVM_DECLARED_TYPE__SUPER_TYPES:
				return superTypes != null && !superTypes.isEmpty();
			case TypesPackage.JVM_DECLARED_TYPE__MEMBERS:
				return members != null && !members.isEmpty();
			case TypesPackage.JVM_DECLARED_TYPE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case TypesPackage.JVM_DECLARED_TYPE__STATIC:
				return static_ != STATIC_EDEFAULT;
			case TypesPackage.JVM_DECLARED_TYPE__FINAL:
				return final_ != FINAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == JvmAnnotationTarget.class)
		{
			switch (derivedFeatureID)
			{
				case TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS: return TypesPackage.JVM_ANNOTATION_TARGET__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == JvmMember.class)
		{
			switch (derivedFeatureID)
			{
				case TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE: return TypesPackage.JVM_MEMBER__DECLARING_TYPE;
				case TypesPackage.JVM_DECLARED_TYPE__VISIBILITY: return TypesPackage.JVM_MEMBER__VISIBILITY;
				case TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME: return TypesPackage.JVM_MEMBER__FULLY_QUALIFIED_NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == JvmAnnotationTarget.class)
		{
			switch (baseFeatureID)
			{
				case TypesPackage.JVM_ANNOTATION_TARGET__ANNOTATIONS: return TypesPackage.JVM_DECLARED_TYPE__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == JvmMember.class)
		{
			switch (baseFeatureID)
			{
				case TypesPackage.JVM_MEMBER__DECLARING_TYPE: return TypesPackage.JVM_DECLARED_TYPE__DECLARING_TYPE;
				case TypesPackage.JVM_MEMBER__VISIBILITY: return TypesPackage.JVM_DECLARED_TYPE__VISIBILITY;
				case TypesPackage.JVM_MEMBER__FULLY_QUALIFIED_NAME: return TypesPackage.JVM_DECLARED_TYPE__FULLY_QUALIFIED_NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (visibility: ");
		result.append(visibility);
		result.append(", fullyQualifiedName: ");
		result.append(fullyQualifiedName);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", static: ");
		result.append(static_);
		result.append(", final: ");
		result.append(final_);
		result.append(')');
		return result.toString();
	}

} //JvmDeclaredTypeImpl