parser grammar LexemeBundleParser;

options {
    tokenVocab=LexemeBundleLexer;
}

lexemes: lexeme+;

lexeme: LEXEME_ID persistentGrammemes? WORD_FORMS_START wordForms WORD_FORMS_END;

persistentGrammemes: grammemes;

wordForms: wordForm+;

wordForm: WORD_FORM grammemes?;

grammemes: LT GRAMMEME+ GT;

