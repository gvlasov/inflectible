lexer grammar TemplateBundleLexer;

NL: '\r'? '\n' -> skip;
ID: SMALL_LETTER ALPHANUM*;
TEMPLATE_START: '{' -> pushMode(TEMPLATE);
fragment ALPHANUM: [a-zA-Z_0-9];
fragment SMALL_LETTER: [a-z];
LPAREN: '(';
RPAREN: ')';
COMMA: ',';
DOT: '.';
WS: ' ' -> skip;

mode TEMPLATE;
TEMPLATE_END: '}' -> popMode;
TEMPLATE_INDENT: ((' '+) | ('\t')) -> pushMode(LINE_CONTENT), skip;
TEMPLATE_NL: NL -> type(NL), skip;

mode LINE_CONTENT;
COMMENT: '//' SEA_CHAR* -> popMode;
ESC: '\\[';
PLACEHOLDER_START: '[' -> pushMode(PLACEHOLDER_ID);
RAW_TEXT: SEA_CHAR+;
LINE_CONTENT_NL: NL -> popMode, type(NL), skip;
fragment SEA_CHAR: ~('\n' | '\r' | '[' | '}');

mode PLACEHOLDER_ID;
NO_GRAMMEME_PLACEHOLDER_END: ']' -> popMode;
GRAMMEMES_TRANSITION: '][' -> pushMode(GRAMMEMES);
CAPITALIZABLE_ID: [a-zA-Z] [a-zA-Z_0-9]*;

mode GRAMMEMES;
GRAMMEMES_WS: WS -> type(WS), skip;
GRAMMEME: Identifier;
PLACEHOLDER_END: ']' -> popMode, popMode; // Pops through PLACEHOLDER_ID
AGREEMENT_DELIMITER: ';' -> pushMode(AGREEMENT);

mode AGREEMENT;
AGREEMENT_ID: [a-z] [a-zA-Z_0-9]* -> popMode;

// Copied from Java.g4 grammar
fragment Identifier: JavaLetter JavaLetterOrDigit*;

fragment JavaLetter
    :   [a-zA-Z$_] // these are the "java letters" below 0xFF
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierStart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

fragment JavaLetterOrDigit
    :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0xFF
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierPart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

