package org.eclipse.xtext.generator.serializer;

import com.google.inject.Inject;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GeneratedJavaClass {
  private final GeneratedJavaClass _this = this;
  @Inject private Grammar grammar;
  @Inject private Naming naming;
  
  public String getName(final String prefix, final String component) {
    String _basePackageRuntime = naming.basePackageRuntime(_this.grammar);
    String _operator_plus = StringExtensions.operator_plus(_basePackageRuntime, ".serializer.");
    final String typeConverted_prefix = (String)prefix;
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, typeConverted_prefix);
    String _name = _this.grammar.getName();
    String _simpleName = naming.toSimpleName(_name);
    String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, _simpleName);
    final String typeConverted_component = (String)component;
    String _operator_plus_3 = StringExtensions.operator_plus(_operator_plus_2, typeConverted_component);
    return _operator_plus_3;
  }
  
  public String getQualifiedName() {
    return null;
  }
  
  public String getPackageName() {
    String _qualifiedName = _this.getQualifiedName();
    String _packageName = naming.toPackageName(_qualifiedName);
    return _packageName;
  }
  
  public String getSimpleName() {
    String _qualifiedName = _this.getQualifiedName();
    String _simpleName = naming.toSimpleName(_qualifiedName);
    return _simpleName;
  }
  
  public String getFileName() {
    String _qualifiedName = _this.getQualifiedName();
    String _asPath = naming.asPath(_qualifiedName);
    String _operator_plus = StringExtensions.operator_plus(_asPath, ".java");
    return _operator_plus;
  }
  
  public String getFileContents() {
    return "<not implemented>";
  }
}