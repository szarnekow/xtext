/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.xtextTest;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cross Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xtext.xtextTest.CrossReference#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtextTest.CrossReference#getTerminal <em>Terminal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xtext.xtextTest.XtextTestPackage#getCrossReference()
 * @model
 * @generated
 */
public interface CrossReference extends AbstractElement
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(TypeRef)
   * @see org.eclipse.xtext.xtextTest.XtextTestPackage#getCrossReference_Type()
   * @model containment="true"
   * @generated
   */
  TypeRef getType();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.xtextTest.CrossReference#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(TypeRef value);

  /**
   * Returns the value of the '<em><b>Terminal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Terminal</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Terminal</em>' containment reference.
   * @see #setTerminal(AbstractElement)
   * @see org.eclipse.xtext.xtextTest.XtextTestPackage#getCrossReference_Terminal()
   * @model containment="true"
   * @generated
   */
  AbstractElement getTerminal();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.xtextTest.CrossReference#getTerminal <em>Terminal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Terminal</em>' containment reference.
   * @see #getTerminal()
   * @generated
   */
  void setTerminal(AbstractElement value);

} // CrossReference
