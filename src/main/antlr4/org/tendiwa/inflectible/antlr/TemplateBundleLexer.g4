lexer grammar TemplateBundleLexer;

import SharedLexer;

NL: '\r'? '\n' -> skip;
ID: SMALL_LETTER ALPHANUM*;
TEMPLATE_START: '{' -> pushMode(TEMPLATE);
LPAREN: '(' -> pushMode(ARGUMENTS);
DOT: '.';
WS: ' ' -> skip;
CAPITALIZED_ARGUMENT_NAME: CAPITAL_LETTER SMALL_LETTER*;
ARGUMENT_NAME: SMALL_LETTER+;
fragment ALPHANUM: [a-zA-Z_0-9];
fragment SMALL_LETTER: [a-z];
fragment CAPITAL_LETTER: [A-Z];

mode ARGUMENTS;
ARG_WS: WS -> type(WS), skip;
ARG: ARGUMENT_NAME -> type(ARGUMENT_NAME);
COMMA: ',';
RPAREN: ')' -> popMode;

mode TEMPLATE;
TEMPLATE_END: '}' -> popMode;
TEMPLATE_INDENT: ((' '+) | ('\t')) -> pushMode(LINE_CONTENT), skip;
TEMPLATE_NL: NL -> type(NL), skip;

mode LINE_CONTENT;
COMMENT: '//' SEA_CHAR* -> popMode;
ESC: '\\[' | '\\\\';
PLACEHOLDER_START: '[' -> pushMode(PLACEHOLDER_ID);
RAW_TEXT: SEA_CHAR+;
LINE_CONTENT_NL: NL -> popMode, type(NL), skip;
fragment SEA_CHAR: ~('\n' | '\r' | '[' | '}' | '\\');

mode PLACEHOLDER_ID;
NO_GRAMMEME_PLACEHOLDER_END: ']' -> popMode;
GRAMMEMES_TRANSITION: ']<' -> pushMode(GRAMMEMES);
KEYWORD_LEXEME: 'lexeme' -> pushMode(STATIC_LEXEME);
CAPITALIZED_KEYWORD_LEXEME: 'Lexeme' -> pushMode(STATIC_LEXEME);
PH_ARG: ARGUMENT_NAME -> type(ARGUMENT_NAME);
PH_ARG_CAPITALIZED: CAPITALIZED_ARGUMENT_NAME -> type(CAPITALIZED_ARGUMENT_NAME);

mode STATIC_LEXEME;
PH_CONCEPT_ID: CONCEPT_ID -> type(CONCEPT_ID), popMode;
KEYWORD_LEXEME_WS: WS -> type(WS), skip;

mode GRAMMEMES;
GRAMMEMES_WS: WS -> type(WS), skip;
PLACEHOLDER_END: '>' -> popMode, popMode; // Pops through PLACEHOLDER_ID
AGREEMENT_DELIMITER: ';' -> pushMode(AGREEMENT);
GR_GRAMMEME: GRAMMEME -> type(GRAMMEME);

mode AGREEMENT;
AGREEMENT_ID: ARGUMENT_NAME -> type(ARGUMENT_NAME), popMode;
