lexer grammar WordBundleLexer;

WS: [ \n\r\t] -> skip;
OPENING_QUOTE: QUOTE -> pushMode(CONCEPTION);
LEXEMES_START: '{' -> pushMode(LEXEMES);
LBRACKET: '[';
RBRACKET: ']';
GRAMMEME: Identifier;
fragment QUOTE: '"';

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

mode CONCEPTION;
CONCEPTION_ID: [a-z] [a-z ]* [a-z];
CLOSING_QUOTE: QUOTE -> popMode;

mode LEXEMES;
LEXEMES_END: '}' -> popMode;
LEXEMES_WS: WS -> type(WS), skip;
LEXEMES_LBRACKET: LBRACKET -> type(LBRACKET), pushMode(LEXEMES_GRAMMEMES);
WORD_FORM: ~[ \n\r\t\[\]]+;

mode LEXEMES_GRAMMEMES;
LEXEMES_GRAMMEMES_RBRACKET: RBRACKET -> type(RBRACKET), popMode;
LEXEMES_GRAMMEME: GRAMMEME -> type(GRAMMEME);
LEXEMES_GRAMMEMES_WS: WS -> type(WS), skip;
