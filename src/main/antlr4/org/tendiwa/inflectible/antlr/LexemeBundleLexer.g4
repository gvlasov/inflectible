lexer grammar LexemeBundleLexer;

import SharedLexer;

WS: [ \n\r\t] -> skip;
WORD_FORMS_START: '{' -> pushMode(WORD_FORMS);
LT: '<';
GT: '>';

mode WORD_FORMS;
WORD_FORMS_END: '}' -> popMode;
WORD_FORMS_WS: WS -> type(WS), skip;
WORD_FORM_LBRACKET: LT -> type(LT), pushMode(WORD_FORM_GRAMMEMES);
WORD_FORM: ~[ \n\r\t\<\>]+;

mode WORD_FORM_GRAMMEMES;
WORD_FORM_GRAMMEMES_RBRACKET: GT -> type(GT), popMode;
WORD_FORM_GRAMMEME: GRAMMEME -> type(GRAMMEME);
WORD_FORM_GRAMMEMES_WS: WS -> type(WS), skip;
