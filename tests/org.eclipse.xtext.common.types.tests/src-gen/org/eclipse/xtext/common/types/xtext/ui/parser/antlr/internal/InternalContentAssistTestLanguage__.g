lexer grammar InternalContentAssistTestLanguage;
@header {
package org.eclipse.xtext.common.types.xtext.ui.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'default' ;
T12 : 'custom' ;
T13 : 'subtype' ;
T14 : 'import' ;
T15 : '.*' ;
T16 : '.' ;

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 366
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 368
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 370
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 372
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 374
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 376
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.xtext.common.types.tests/src-gen/org/eclipse/xtext/common/types/xtext/ui/parser/antlr/internal/InternalContentAssistTestLanguage.g" 378
RULE_ANY_OTHER : .;

