package org.eclipse.xtext.generator.serializer;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.grammarAccess.GrammarAccess;
import org.eclipse.xtext.generator.serializer.GeneratedFile;
import org.eclipse.xtext.generator.serializer.JavaEMFFile;
import org.eclipse.xtext.generator.serializer.SemanticSequencer;
import org.eclipse.xtext.generator.serializer.SemanticSequencerUtil;
import org.eclipse.xtext.serializer.analysis.Context2NameFunction;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IConstraint;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IConstraintElement;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IFeatureInfo;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class AbstractSemanticSequencer extends GeneratedFile {
  @Inject
  private Grammar grammar;
  
  @Inject
  private GrammarAccess grammarAccess;
  
  @Inject
  private SemanticSequencerUtil sequencerUtil;
  
  @Inject
  private SemanticSequencer sequencer;
  
  @Inject
  private Context2NameFunction ctx2name;
  
  public String getQualifiedName(final Grammar grammar) {
    String _name = this.getName(grammar, "Abstract", "SemanticSequencer");
    return _name;
  }
  
  public <T extends ENamedElement> List<T> sort(final Iterable<T> iterable) {
    final Function2<T,T,Integer> _function = new Function2<T,T,Integer>() {
        public Integer apply(final T p1, final T p2) {
          String _name = p1.getName();
          String _name_1 = p2.getName();
          int _compareTo = _name.compareTo(_name_1);
          return _compareTo;
        }
      };
    List<T> _sort = IterableExtensions.<T>sort(iterable, new Comparator<T>() {
        public int compare(T o1,T o2) {
          return _function.apply(o1,o2);
        }
    });
    return _sort;
  }
  
  public Iterable<EPackage> getAccessedPackages() {
    Collection<IConstraint> _grammarConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar);
    final Function1<IConstraint,Boolean> _function = new Function1<IConstraint,Boolean>() {
        public Boolean apply(final IConstraint e) {
          EClass _type = e.getType();
          boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_type, null);
          return Boolean.valueOf(_operator_notEquals);
        }
      };
    Iterable<IConstraint> _filter = IterableExtensions.<IConstraint>filter(_grammarConstraints, _function);
    final Function1<IConstraint,EPackage> _function_1 = new Function1<IConstraint,EPackage>() {
        public EPackage apply(final IConstraint e) {
          EClass _type = e.getType();
          EPackage _ePackage = _type.getEPackage();
          return _ePackage;
        }
      };
    Iterable<EPackage> _map = IterableExtensions.<IConstraint, EPackage>map(_filter, _function_1);
    Set<EPackage> _set = IterableExtensions.<EPackage>toSet(_map);
    List<EPackage> _sort = this.<EPackage>sort(_set);
    return _sort;
  }
  
  public Iterable<EClass> getAccessedClasses(final EPackage pkg) {
    Collection<IConstraint> _grammarConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar);
    final Function1<IConstraint,EClass> _function = new Function1<IConstraint,EClass>() {
        public EClass apply(final IConstraint e) {
          EClass _type = e.getType();
          return _type;
        }
      };
    Iterable<EClass> _map = IterableExtensions.<IConstraint, EClass>map(_grammarConstraints, _function);
    final Function1<EClass,Boolean> _function_1 = new Function1<EClass,Boolean>() {
        public Boolean apply(final EClass e) {
          boolean _operator_and = false;
          boolean _operator_notEquals = ObjectExtensions.operator_notEquals(e, null);
          if (!_operator_notEquals) {
            _operator_and = false;
          } else {
            EPackage _ePackage = e.getEPackage();
            boolean _operator_equals = ObjectExtensions.operator_equals(_ePackage, pkg);
            _operator_and = BooleanExtensions.operator_and(_operator_notEquals, _operator_equals);
          }
          return Boolean.valueOf(_operator_and);
        }
      };
    Iterable<EClass> _filter = IterableExtensions.<EClass>filter(_map, _function_1);
    Set<EClass> _set = IterableExtensions.<EClass>toSet(_filter);
    List<EClass> _sort = this.<EClass>sort(_set);
    return _sort;
  }
  
  public Iterable<EClass> getAccessedClasses() {
    Collection<IConstraint> _grammarConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar);
    final Function1<IConstraint,EClass> _function = new Function1<IConstraint,EClass>() {
        public EClass apply(final IConstraint e) {
          EClass _type = e.getType();
          return _type;
        }
      };
    Iterable<EClass> _map = IterableExtensions.<IConstraint, EClass>map(_grammarConstraints, _function);
    Set<EClass> _set = IterableExtensions.<EClass>toSet(_map);
    List<EClass> _sort = this.<EClass>sort(_set);
    return _sort;
  }
  
  public Map<IConstraint,List<EObject>> getAccessedConstraints(final EClass clazz) {
    Map<IConstraint,List<EObject>> _grammarConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar, clazz);
    return _grammarConstraints;
  }
  
  public Collection<EObject> getAccessedContexts(final EClass clazz) {
    Collection<EObject> _grammarContexts = this.sequencerUtil.getGrammarContexts(this.grammar, clazz);
    return _grammarContexts;
  }
  
  public Collection<EObject> getAccessedContexts() {
    Collection<EObject> _grammarContexts = this.sequencerUtil.getGrammarContexts(this.grammar);
    return _grammarContexts;
  }
  
  public Collection<IConstraint> getAccessedConstraints() {
    Collection<IConstraint> _grammarConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar);
    return _grammarConstraints;
  }
  
  public HashSet<Grammar> getGrammars(final IConstraintElement ele) {
      HashSet<Grammar> _newHashSet = CollectionLiterals.<Grammar>newHashSet();
      final HashSet<Grammar> result = _newHashSet;
      boolean _operator_and = false;
      boolean _operator_notEquals = ObjectExtensions.operator_notEquals(ele, null);
      if (!_operator_notEquals) {
        _operator_and = false;
      } else {
        AbstractElement _grammarElement = ele.getGrammarElement();
        boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(_grammarElement, null);
        _operator_and = BooleanExtensions.operator_and(_operator_notEquals, _operator_notEquals_1);
      }
      if (_operator_and) {
        AbstractElement _grammarElement_1 = ele.getGrammarElement();
        Grammar _grammar = GrammarUtil.getGrammar(_grammarElement_1);
        result.add(_grammar);
      }
      boolean _operator_and_1 = false;
      boolean _operator_notEquals_2 = ObjectExtensions.operator_notEquals(ele, null);
      if (!_operator_notEquals_2) {
        _operator_and_1 = false;
      } else {
        List<IConstraintElement> _children = ele.getChildren();
        boolean _operator_notEquals_3 = ObjectExtensions.operator_notEquals(_children, null);
        _operator_and_1 = BooleanExtensions.operator_and(_operator_notEquals_2, _operator_notEquals_3);
      }
      if (_operator_and_1) {
        List<IConstraintElement> _children_1 = ele.getChildren();
        final Function1<IConstraintElement,HashSet<Grammar>> _function = new Function1<IConstraintElement,HashSet<Grammar>>() {
            public HashSet<Grammar> apply(final IConstraintElement c) {
              HashSet<Grammar> _grammars = AbstractSemanticSequencer.this.getGrammars(c);
              return _grammars;
            }
          };
        List<HashSet<Grammar>> _map = ListExtensions.<IConstraintElement, HashSet<Grammar>>map(_children_1, _function);
        Iterable<Grammar> _flatten = IterableExtensions.<Grammar>flatten(_map);
        for (final Grammar g : _flatten) {
          result.add(g);
        }
      }
      return result;
  }
  
  public boolean uses(final Grammar g1, final Grammar g2) {
    boolean _operator_and = false;
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(g1, null);
    if (!_operator_notEquals) {
      _operator_and = false;
    } else {
      EList<Grammar> _usedGrammars = g1.getUsedGrammars();
      final Function1<Grammar,Boolean> _function = new Function1<Grammar,Boolean>() {
          public Boolean apply(final Grammar e) {
            boolean _operator_or = false;
            boolean _operator_equals = ObjectExtensions.operator_equals(e, g2);
            if (_operator_equals) {
              _operator_or = true;
            } else {
              boolean _uses = AbstractSemanticSequencer.this.uses(e, g2);
              _operator_or = BooleanExtensions.operator_or(_operator_equals, _uses);
            }
            return Boolean.valueOf(_operator_or);
          }
        };
      boolean _exists = IterableExtensions.<Grammar>exists(_usedGrammars, _function);
      _operator_and = BooleanExtensions.operator_and(_operator_notEquals, _exists);
    }
    return _operator_and;
  }
  
  public Grammar getMostConcreteGrammar(final IConstraint constraint) {
    IConstraintElement _body = constraint.getBody();
    HashSet<Grammar> _grammars = this.getGrammars(_body);
    final Function2<Grammar,Grammar,Grammar> _function = new Function2<Grammar,Grammar,Grammar>() {
        public Grammar apply(final Grammar x, final Grammar y) {
          Grammar _xifexpression = null;
          boolean _uses = AbstractSemanticSequencer.this.uses(x, y);
          if (_uses) {
            _xifexpression = x;
          } else {
            _xifexpression = y;
          }
          return _xifexpression;
        }
      };
    Grammar _reduce = IterableExtensions.<Grammar>reduce(_grammars, _function);
    return _reduce;
  }
  
  public boolean usesSuperGrammar() {
    Collection<IConstraint> _accessedConstraints = this.getAccessedConstraints();
    final Function1<IConstraint,Grammar> _function = new Function1<IConstraint,Grammar>() {
        public Grammar apply(final IConstraint c) {
          Grammar _mostConcreteGrammar = AbstractSemanticSequencer.this.getMostConcreteGrammar(c);
          return _mostConcreteGrammar;
        }
      };
    Iterable<Grammar> _map = IterableExtensions.<IConstraint, Grammar>map(_accessedConstraints, _function);
    final Function1<Grammar,Boolean> _function_1 = new Function1<Grammar,Boolean>() {
        public Boolean apply(final Grammar g) {
          boolean _operator_and = false;
          boolean _operator_notEquals = ObjectExtensions.operator_notEquals(g, null);
          if (!_operator_notEquals) {
            _operator_and = false;
          } else {
            boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(g, AbstractSemanticSequencer.this.grammar);
            _operator_and = BooleanExtensions.operator_and(_operator_notEquals, _operator_notEquals_1);
          }
          return Boolean.valueOf(_operator_and);
        }
      };
    boolean _exists = IterableExtensions.<Grammar>exists(_map, _function_1);
    return _exists;
  }
  
  public CharSequence getFileContents() {
    String _xblockexpression = null;
    {
      Resource _eResource = this.grammar.eResource();
      ResourceSet _resourceSet = _eResource.getResourceSet();
      String _packageName = this.getPackageName();
      JavaEMFFile _javaEMFFile = new JavaEMFFile(_resourceSet, _packageName);
      final JavaEMFFile file = _javaEMFFile;
      file.imported(org.eclipse.emf.ecore.EObject.class);
      file.imported(org.eclipse.xtext.serializer.sequencer.GenericSequencer.class);
      file.imported(org.eclipse.xtext.serializer.sequencer.ISemanticSequencer.class);
      file.imported(org.eclipse.xtext.serializer.sequencer.ITransientValueService.class);
      file.imported(org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor.class);
      file.imported(org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider.class);
      file.imported(org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor.class);
      file.imported(org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer.class);
      file.imported(com.google.inject.Inject.class);
      file.imported(com.google.inject.Provider.class);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@SuppressWarnings(\"restriction\")");
      _builder.newLine();
      _builder.append("public class ");
      String _simpleName = this.getSimpleName();
      _builder.append(_simpleName, "");
      _builder.append(" extends AbstractSemanticSequencer {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Inject");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected ");
      String _gaFQName = this.grammarAccess.gaFQName(this.grammar);
      String _imported = file.imported(_gaFQName);
      _builder.append(_imported, "	");
      _builder.append(" grammarAccess;");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Inject");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected ISemanticSequencerDiagnosticProvider diagnosticProvider;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Inject");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected ITransientValueService transientValues;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Inject");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@GenericSequencer");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected Provider<ISemanticSequencer> genericSequencerProvider;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected ISemanticSequencer genericSequencer;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      {
        boolean _usesSuperGrammar = this.usesSuperGrammar();
        if (_usesSuperGrammar) {
          _builder.append("\t");
          EList<Grammar> _usedGrammars = this.grammar.getUsedGrammars();
          Grammar _head = IterableExtensions.<Grammar>head(_usedGrammars);
          String _qualifiedName = this.sequencer.getQualifiedName(_head);
          String _imported_1 = file.imported(_qualifiedName);
          final String superGrammar = _imported_1;
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("@Inject");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("protected Provider<");
          _builder.append(superGrammar, "	");
          _builder.append("> superSequencerProvider;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(" ");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("protected ");
          _builder.append(superGrammar, "	");
          _builder.append(" superSequencer; ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("super.init(sequencer, sequenceAcceptor, errorAcceptor);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.genericSequencer = genericSequencerProvider.get();");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);");
      _builder.newLine();
      {
        boolean _usesSuperGrammar_1 = this.usesSuperGrammar();
        if (_usesSuperGrammar_1) {
          _builder.append("\t\t");
          _builder.append("this.superSequencer = superSequencerProvider.get();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("this.superSequencer.init(sequencer, sequenceAcceptor, errorAcceptor); ");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _genMethodCreateSequence = this.genMethodCreateSequence(file);
      _builder.append(_genMethodCreateSequence, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      Collection<IConstraint> _accessedConstraints = this.getAccessedConstraints();
      final Function1<IConstraint,Boolean> _function = new Function1<IConstraint,Boolean>() {
          public Boolean apply(final IConstraint e) {
            EClass _type = e.getType();
            boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_type, null);
            return Boolean.valueOf(_operator_notEquals);
          }
        };
      Iterable<IConstraint> _filter = IterableExtensions.<IConstraint>filter(_accessedConstraints, _function);
      final Function1<IConstraint,CharSequence> _function_1 = new Function1<IConstraint,CharSequence>() {
          public CharSequence apply(final IConstraint e) {
            CharSequence _genMethodSequence = AbstractSemanticSequencer.this.genMethodSequence(file, e);
            return _genMethodSequence;
          }
        };
      String _join = IterableExtensions.<IConstraint>join(_filter, "\n\n", _function_1);
      _builder.append(_join, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      String _string = _builder.toString();
      file.setBody(_string);
      String _string_1 = file.toString();
      _xblockexpression = (_string_1);
    }
    return _xblockexpression;
  }
  
  public CharSequence genMethodCreateSequence(final JavaEMFFile file) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void createSequence(EObject context, EObject semanticObject) {");
    _builder.newLine();
    _builder.append("\t");
    int pkgi = 0;
    _builder.newLineIfNotEmpty();
    {
      Iterable<EPackage> _accessedPackages = this.getAccessedPackages();
      for(final EPackage pkg : _accessedPackages) {
        _builder.append("\t");
        {
          int _operator_plus = IntegerExtensions.operator_plus(pkgi, 1);
          int _pkgi = pkgi = _operator_plus;
          boolean _operator_greaterThan = IntegerExtensions.operator_greaterThan(_pkgi, 1);
          if (_operator_greaterThan) {
            _builder.append("else ");
          }
        }
        _builder.append("if(semanticObject.eClass().getEPackage() == ");
        String _importedGenTypeLiteral = file.importedGenTypeLiteral(pkg);
        _builder.append(_importedGenTypeLiteral, "	");
        _builder.append(") switch(semanticObject.eClass().getClassifierID()) {");
        _builder.newLineIfNotEmpty();
        {
          Iterable<EClass> _accessedClasses = this.getAccessedClasses(pkg);
          for(final EClass type : _accessedClasses) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("case ");
            String _importedGenIntLiteral = file.importedGenIntLiteral(type);
            _builder.append(_importedGenIntLiteral, "		");
            _builder.append(":");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\t");
            int ctxi = 0;
            _builder.newLineIfNotEmpty();
            {
              Map<IConstraint,List<EObject>> _accessedConstraints = this.getAccessedConstraints(type);
              Set<Entry<IConstraint,List<EObject>>> _entrySet = _accessedConstraints.entrySet();
              final Function1<Entry<IConstraint,List<EObject>>,String> _function = new Function1<Entry<IConstraint,List<EObject>>,String>() {
                  public String apply(final Entry<IConstraint,List<EObject>> e) {
                    IConstraint _key = e.getKey();
                    String _name = _key.getName();
                    return _name;
                  }
                };
              List<Entry<IConstraint,List<EObject>>> _sortBy = IterableExtensions.<Entry<IConstraint,List<EObject>>, String>sortBy(_entrySet, _function);
              for(final Entry<IConstraint,List<EObject>> ctx : _sortBy) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                {
                  int _operator_plus_1 = IntegerExtensions.operator_plus(ctxi, 1);
                  int _ctxi = ctxi = _operator_plus_1;
                  boolean _operator_greaterThan_1 = IntegerExtensions.operator_greaterThan(_ctxi, 1);
                  if (_operator_greaterThan_1) {
                    _builder.append("else ");
                  }
                }
                _builder.append("if(");
                {
                  List<EObject> _value = ctx.getValue();
                  final Function1<EObject,String> _function_1 = new Function1<EObject,String>() {
                      public String apply(final EObject e) {
                        String _contextName = AbstractSemanticSequencer.this.ctx2name.getContextName(e);
                        return _contextName;
                      }
                    };
                  List<EObject> _sortBy_1 = IterableExtensions.<EObject, String>sortBy(_value, _function_1);
                  boolean _hasElements = false;
                  for(final EObject c : _sortBy_1) {
                    if (!_hasElements) {
                      _hasElements = true;
                    } else {
                      _builder.appendImmediate(" ||\n   ", "			");
                    }
                    _builder.append("context == grammarAccess.");
                    String _gaAccessor = this.grammarAccess.gaAccessor(c);
                    _builder.append(_gaAccessor, "			");
                  }
                }
                _builder.append(") {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("sequence_");
                IConstraint _key = ctx.getKey();
                String _simpleName = _key.getSimpleName();
                _builder.append(_simpleName, "				");
                _builder.append("(context, (");
                String _importedGenTypeName = file.importedGenTypeName(type);
                _builder.append(_importedGenTypeName, "				");
                _builder.append(") semanticObject); ");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return; ");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("else break;");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence genMethodSequence(final JavaEMFFile file, final IConstraint c) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constraint:");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*     ");
    String _xifexpression = null;
    IConstraintElement _body = c.getBody();
    boolean _operator_equals = ObjectExtensions.operator_equals(_body, null);
    if (_operator_equals) {
      EClass _type = c.getType();
      String _name = _type.getName();
      String _operator_plus = StringExtensions.operator_plus("{", _name);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "}");
      _xifexpression = _operator_plus_1;
    } else {
      IConstraintElement _body_1 = c.getBody();
      String _string = _body_1.toString();
      String _replaceAll = _string.replaceAll("\\n", "\n*     ");
      _xifexpression = _replaceAll;
    }
    _builder.append(_xifexpression, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected void sequence_");
    String _simpleName = c.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append("(EObject context, ");
    EClass _type_1 = c.getType();
    String _importedGenTypeName = file.importedGenTypeName(_type_1);
    _builder.append(_importedGenTypeName, "");
    _builder.append(" semanticObject) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EClass _type_2 = c.getType();
    String _eObjectCast = file.getEObjectCast(_type_2);
    final String cast = _eObjectCast;
    _builder.newLineIfNotEmpty();
    {
      HashSet<Grammar> _newHashSet = CollectionLiterals.<Grammar>newHashSet(this.grammar, null);
      Grammar _mostConcreteGrammar = this.getMostConcreteGrammar(c);
      boolean _contains = _newHashSet.contains(_mostConcreteGrammar);
      boolean _operator_not = BooleanExtensions.operator_not(_contains);
      if (_operator_not) {
        _builder.append("\t");
        _builder.append("superSequencer.createSequence(context, ");
        _builder.append(cast, "	");
        _builder.append("semanticObject);");
        _builder.newLineIfNotEmpty();
      } else {
        boolean _canGenerate = this.sequencerUtil.canGenerate(c);
        if (_canGenerate) {
          _builder.append("\t");
          _builder.append("if(errorAcceptor != null) {");
          _builder.newLine();
          {
            IFeatureInfo[] _features = c.getFeatures();
            final Function1<IFeatureInfo,Boolean> _function = new Function1<IFeatureInfo,Boolean>() {
                public Boolean apply(final IFeatureInfo e) {
                  boolean _operator_notEquals = ObjectExtensions.operator_notEquals(e, null);
                  return Boolean.valueOf(_operator_notEquals);
                }
              };
            Iterable<IFeatureInfo> _filter = IterableExtensions.<IFeatureInfo>filter(((Iterable<IFeatureInfo>)Conversions.doWrapArray(_features)), _function);
            for(final IFeatureInfo f : _filter) {
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("if(transientValues.isValueTransient(");
              _builder.append(cast, "		");
              _builder.append("semanticObject, ");
              EStructuralFeature _feature = f.getFeature();
              String _importedGenTypeLiteral = file.importedGenTypeLiteral(_feature);
              _builder.append(_importedGenTypeLiteral, "		");
              _builder.append(") == ");
              String _imported = file.imported(org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient.class);
              _builder.append(_imported, "		");
              _builder.append(".YES)");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(");
              _builder.append(cast, "			");
              _builder.append("semanticObject, ");
              EStructuralFeature _feature_1 = f.getFeature();
              String _importedGenTypeLiteral_1 = file.importedGenTypeLiteral(_feature_1);
              _builder.append(_importedGenTypeLiteral_1, "			");
              _builder.append("));");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          String _imported_1 = file.imported(org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider.class);
          _builder.append(_imported_1, "	");
          _builder.append(" nodes = createNodeProvider(");
          _builder.append(cast, "	");
          _builder.append("semanticObject);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _imported_2 = file.imported(org.eclipse.xtext.serializer.acceptor.SequenceFeeder.class);
          _builder.append(_imported_2, "	");
          _builder.append(" feeder = createSequencerFeeder(");
          _builder.append(cast, "	");
          _builder.append("semanticObject, nodes);");
          _builder.newLineIfNotEmpty();
          {
            Iterable<IFeatureInfo> _xifexpression_1 = null;
            IConstraintElement _body_2 = c.getBody();
            IFeatureInfo _featureInfo = _body_2.getFeatureInfo();
            boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_featureInfo, null);
            if (_operator_notEquals) {
              IConstraintElement _body_3 = c.getBody();
              IFeatureInfo _featureInfo_1 = _body_3.getFeatureInfo();
              ArrayList<IFeatureInfo> _newArrayList = CollectionLiterals.<IFeatureInfo>newArrayList(_featureInfo_1);
              _xifexpression_1 = _newArrayList;
            } else {
              IConstraintElement _body_4 = c.getBody();
              List<IConstraintElement> _children = _body_4.getChildren();
              final Function1<IConstraintElement,Boolean> _function_1 = new Function1<IConstraintElement,Boolean>() {
                  public Boolean apply(final IConstraintElement e) {
                    IFeatureInfo _featureInfo = e.getFeatureInfo();
                    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_featureInfo, null);
                    return Boolean.valueOf(_operator_notEquals);
                  }
                };
              Iterable<IConstraintElement> _filter_1 = IterableExtensions.<IConstraintElement>filter(_children, _function_1);
              final Function1<IConstraintElement,IFeatureInfo> _function_2 = new Function1<IConstraintElement,IFeatureInfo>() {
                  public IFeatureInfo apply(final IConstraintElement e) {
                    IFeatureInfo _featureInfo = e.getFeatureInfo();
                    return _featureInfo;
                  }
                };
              Iterable<IFeatureInfo> _map = IterableExtensions.<IConstraintElement, IFeatureInfo>map(_filter_1, _function_2);
              _xifexpression_1 = _map;
            }
            for(final IFeatureInfo f_1 : _xifexpression_1) {
              _builder.append("\t");
              IConstraintElement[] _assignments = f_1.getAssignments();
              IConstraintElement _get = ((List<IConstraintElement>)Conversions.doWrapArray(_assignments)).get(0);
              final IConstraintElement assignment = _get;
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("feeder.accept(grammarAccess.");
              AbstractElement _grammarElement = assignment.getGrammarElement();
              String _gaAccessor = this.grammarAccess.gaAccessor(_grammarElement);
              _builder.append(_gaAccessor, "	");
              _builder.append(", semanticObject.");
              EStructuralFeature _feature_2 = f_1.getFeature();
              String _getAccessor = file.getGetAccessor(_feature_2);
              _builder.append(_getAccessor, "	");
              _builder.append("());");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("feeder.finish();");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("genericSequencer.createSequence(context, ");
          _builder.append(cast, "	");
          _builder.append("semanticObject);");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
