/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.xtend2.xtend2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.xtext.xtend2.xtend2.Xtend2Package
 * @generated
 */
public interface Xtend2Factory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Xtend2Factory eINSTANCE = org.eclipse.xtext.xtend2.xtend2.impl.Xtend2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Xtend File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xtend File</em>'.
	 * @generated
	 */
	XtendFile createXtendFile();

	/**
	 * Returns a new object of class '<em>Xtend Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xtend Import</em>'.
	 * @generated
	 */
	XtendImport createXtendImport();

	/**
	 * Returns a new object of class '<em>Xtend Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xtend Function</em>'.
	 * @generated
	 */
	XtendFunction createXtendFunction();

	/**
	 * Returns a new object of class '<em>Xtend Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xtend Class</em>'.
	 * @generated
	 */
	XtendClass createXtendClass();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Xtend2Package getXtend2Package();

} //Xtend2Factory