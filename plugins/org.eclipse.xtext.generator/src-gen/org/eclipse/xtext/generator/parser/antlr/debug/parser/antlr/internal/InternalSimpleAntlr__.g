lexer grammar InternalSimpleAntlr;
@header {
package org.eclipse.xtext.generator.parser.antlr.debug.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'grammar' ;
T12 : ';' ;
T13 : 'options' ;
T14 : '{' ;
T15 : '}' ;
T16 : '=' ;
T17 : ':' ;
T18 : '|' ;
T19 : '+' ;
T20 : '*' ;
T21 : '?' ;
T22 : '~' ;
T23 : '..' ;
T24 : '(' ;
T25 : ')' ;
T26 : '{skip();}' ;
T27 : '.' ;

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1251
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1253
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1255
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1257
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1259
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1261
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.xtext.generator/src-gen/org/eclipse/xtext/generator/parser/antlr/debug/parser/antlr/internal/InternalSimpleAntlr.g" 1263
RULE_ANY_OTHER : .;

