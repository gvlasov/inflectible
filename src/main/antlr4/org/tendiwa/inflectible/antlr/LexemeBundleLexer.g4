lexer grammar LexemeBundleLexer;

WS: [ \n\r\t] -> skip;
LEXEME_NAME: [A-Z] [A-Z.]* [A-Z];
WORD_FORMS_START: '{' -> pushMode(WORD_FORMS);
LT: '<';
GT: '>';
GRAMMEME: Identifier;

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

mode WORD_FORMS;
WORD_FORMS_END: '}' -> popMode;
WORD_FORMS_WS: WS -> type(WS), skip;
WORD_FORM_LBRACKET: LT -> type(LT), pushMode(WORD_FORM_GRAMMEMES);
WORD_FORM: ~[ \n\r\t\<\>]+;

mode WORD_FORM_GRAMMEMES;
WORD_FORM_GRAMMEMES_RBRACKET: GT -> type(GT), popMode;
WORD_FORM_GRAMMEME: GRAMMEME -> type(GRAMMEME);
WORD_FORM_GRAMMEMES_WS: WS -> type(WS), skip;
