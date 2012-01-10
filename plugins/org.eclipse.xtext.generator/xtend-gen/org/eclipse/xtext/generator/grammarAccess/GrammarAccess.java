package org.eclipse.xtext.generator.grammarAccess;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.grammarAccess.GrammarAccessUtil;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Moritz Eysholdt
 */
@SuppressWarnings("all")
public class GrammarAccess {
  @Inject
  private Naming naming;
  
  /**
   * Returns the invokation of the element accessor method as full qualified Java statement.
   * Example: return FowlerDslTestLanguageGrammarAccess.INSTANCE.prStatemachine().ele1AssignmentStates()
   * @param ele the element
   * @return The java statement
   */
  public String gaFullAccessor(final AbstractElement ele) {
    Grammar _grammar = GrammarUtil.getGrammar(ele);
    String _gaSimpleName = this.gaSimpleName(_grammar);
    String _operator_plus = StringExtensions.operator_plus(_gaSimpleName, ".INSTANCE.");
    String _gaRuleElementAccessor = this.gaRuleElementAccessor(ele);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _gaRuleElementAccessor);
    return _operator_plus_1;
  }
  
  /**
   * Converts an arbitary string to a valid Java identifier
   * The string is being split up along the the caracters that are not valid as java
   * identifier. The first character of each segments is made upper case which
   * leads to a camel-case style.
   * @param text the string
   * @param uppercaseFirst whether the first character of the returned identifier should be uppercase or lowercase
   * @return the java identifier
   */
  public String toJavaIdentifier(final String text, final boolean uppercaseFirst) {
    String _javaIdentifier = GrammarAccessUtil.toJavaIdentifier(text, Boolean.valueOf(uppercaseFirst));
    return _javaIdentifier;
  }
  
  /**
   * Creates an identifier for a Rule which is a valid Java idetifier and unique within
   * the Rule's grammar.
   * @param rule the Rule
   * @return the identifier
   */
  public String gaRuleIdentifyer(final AbstractRule rule) {
    String _name = rule.getName();
    String _javaIdentifier = this.toJavaIdentifier(_name, true);
    return _javaIdentifier;
  }
  
  /**
   * Creates an identifier for an AbstractElement which is a valid Java identifier and
   * which is unique whithin the element's rule. The identifier tries to be as
   * human-readable as possible.
   * @param element the element
   * @return the element's identifier
   */
  public String gaElementIdentifyer(final AbstractElement element) {
    String _uniqueElementName = GrammarAccessUtil.getUniqueElementName(element);
    return _uniqueElementName;
  }
  
  /**
   * Returns the full qulified Java class name of a GrammarAccess implementation for a grammar.
   * @param grammar
   * @return the GrammarAccess' full qualified Java class name
   */
  public String gaFQName(final Grammar grammar) {
    String _grammarAccessFQName = GrammarAccessUtil.getGrammarAccessFQName(grammar, this.naming);
    return _grammarAccessFQName;
  }
  
  /**
   * Returns the simple Java class name of a GrammarAccess implementation for a Grammar.
   * The simple class name is defined as the class' name without any package names.
   * @param grammar the grammar
   * @return the GrammarAccess' simple class name
   */
  public String gaSimpleName(final Grammar grammar) {
    String _gaFQName = this.gaFQName(grammar);
    String _simpleName = this.naming.toSimpleName(_gaFQName);
    return _simpleName;
  }
  
  /**
   * Returns the method name for accessing a rule via a GrammarAccess implementation.
   * @param rule the rule for which the accessor method is needed
   * @return the method's name.
   */
  public String gaRuleAccessMethodName(final AbstractRule rule) {
    String _gaRuleIdentifyer = this.gaRuleIdentifyer(rule);
    String _operator_plus = StringExtensions.operator_plus("get", _gaRuleIdentifyer);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "Rule");
    return _operator_plus_1;
  }
  
  /**
   * Returns the method name for accessing a rule's content via a ParseRuleAccess implementation.
   * @param rule the rule for which the accessor method is needed
   * @return the method's name.
   */
  public String gaRuleElementsMethodName(final AbstractRule rule) {
    String _gaRuleIdentifyer = this.gaRuleIdentifyer(rule);
    String _operator_plus = StringExtensions.operator_plus("get", _gaRuleIdentifyer);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "Access");
    return _operator_plus_1;
  }
  
  /**
   * Returns the method name for accessing an element via a GrammarAccess implementation.
   * @param element the element for which the accesor method is needed
   * @return the method's name.
   */
  public String gaElementAccessMethodeName(final AbstractElement element) {
    String _gaElementIdentifyer = this.gaElementIdentifyer(element);
    String _operator_plus = StringExtensions.operator_plus("get", _gaElementIdentifyer);
    return _operator_plus;
  }
  
  /**
   * Returns the simple class name of a rule's facade. A GrammarAccess implementation has
   * a facade for each parser rule, which contains the methods for accessing the rule's elements.
   * @param rule
   * @return the simple class name
   */
  public String gaRuleAccesorClassName(final AbstractRule rule) {
    String _gaRuleIdentifyer = this.gaRuleIdentifyer(rule);
    String _operator_plus = StringExtensions.operator_plus(_gaRuleIdentifyer, "Elements");
    return _operator_plus;
  }
  
  /**
   * Returns the invokation of the rule accessor method as Java statement.
   * @param rule the rule
   * @return The java statement
   */
  public String gaRuleAccessor(final AbstractRule rule) {
    String _gaRuleAccessMethodName = this.gaRuleAccessMethodName(rule);
    String _operator_plus = StringExtensions.operator_plus(_gaRuleAccessMethodName, "()");
    return _operator_plus;
  }
  
  /**
   * Returns the invokation of the rule's content accessor method as Java statement.
   * @param rule the rule
   * @return The java statement
   */
  public String gaElementsAccessor(final AbstractRule rule) {
    String _gaRuleElementsMethodName = this.gaRuleElementsMethodName(rule);
    String _operator_plus = StringExtensions.operator_plus(_gaRuleElementsMethodName, "()");
    return _operator_plus;
  }
  
  /**
   * Returns the invokation of the element accessor method as Java statement.
   * The called method is implemented by the rule's facade.
   * Example: ele1AssignmentStates()
   * @param ele the element
   * @return The java statement
   */
  public String gaElementAccessor(final AbstractElement ele) {
    String _gaElementAccessMethodeName = this.gaElementAccessMethodeName(ele);
    String _operator_plus = StringExtensions.operator_plus(_gaElementAccessMethodeName, "()");
    return _operator_plus;
  }
  
  /**
   * Returns the invokation of the element accessor method for a GrammarAccess
   * as Java statement.
   * Example: prStatemachine().ele1AssignmentStates()
   * @param ele the element
   * @return The java statement
   */
  public String gaRuleElementAccessor(final AbstractElement ele) {
    AbstractRule _containingRule = GrammarUtil.containingRule(ele);
    String _gaElementsAccessor = this.gaElementsAccessor(_containingRule);
    String _operator_plus = StringExtensions.operator_plus(_gaElementsAccessor, ".");
    String _gaElementAccessor = this.gaElementAccessor(ele);
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, _gaElementAccessor);
    return _operator_plus_1;
  }
  
  /**
   * Returns the invokation of an element or rule accessor, including the .getType() call.
   * Example1: getFooRule().getType()
   * Example2: getBarRule().getFooAction().getType()
   */
  public String gaTypeAccessor(final TypeRef ele) {
    String _switchResult = null;
    EObject _eContainer = ele.eContainer();
    final EObject cnt = _eContainer;
    boolean matched = false;
    if (!matched) {
      if (cnt instanceof AbstractElement) {
        final AbstractElement _abstractElement = (AbstractElement)cnt;
        matched=true;
        String _gaRuleElementAccessor = this.gaRuleElementAccessor(_abstractElement);
        String _operator_plus = StringExtensions.operator_plus(_gaRuleElementAccessor, ".getType()");
        _switchResult = _operator_plus;
      }
    }
    if (!matched) {
      if (cnt instanceof AbstractRule) {
        final AbstractRule _abstractRule = (AbstractRule)cnt;
        matched=true;
        String _gaRuleAccessor = this.gaRuleAccessor(_abstractRule);
        String _operator_plus = StringExtensions.operator_plus(_gaRuleAccessor, ".getType()");
        _switchResult = _operator_plus;
      }
    }
    if (!matched) {
      EObject _eContainer_1 = ele.eContainer();
      EClass _eClass = _eContainer_1.eClass();
      String _name = _eClass.getName();
      String _operator_plus = StringExtensions.operator_plus("<error: unknown type ", _name);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, ">");
      _switchResult = _operator_plus_1;
    }
    return _switchResult;
  }
  
  /**
   * Returns the invokation of an element or rule accessor.
   * Example1: getFooRule()
   * Example2: getBarRule().getFooAction()
   */
  public String gaAccessor(final EObject ele) {
    String _switchResult = null;
    boolean matched = false;
    if (!matched) {
      if (ele instanceof AbstractElement) {
        final AbstractElement _abstractElement = (AbstractElement)ele;
        matched=true;
        String _gaRuleElementAccessor = this.gaRuleElementAccessor(_abstractElement);
        _switchResult = _gaRuleElementAccessor;
      }
    }
    if (!matched) {
      if (ele instanceof AbstractRule) {
        final AbstractRule _abstractRule = (AbstractRule)ele;
        matched=true;
        String _gaRuleAccessor = this.gaRuleAccessor(_abstractRule);
        _switchResult = _gaRuleAccessor;
      }
    }
    if (!matched) {
      EClass _eClass = ele.eClass();
      String _name = _eClass.getName();
      String _operator_plus = StringExtensions.operator_plus("<error: unknown type ", _name);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, ">");
      _switchResult = _operator_plus_1;
    }
    return _switchResult;
  }
  
  public String grammarFragmentToStr(final EObject ele, final String prefix) {
    String _serialize = GrammarAccessUtil.serialize(ele, prefix);
    return _serialize;
  }
}
