parser grammar LexemeBundleParser;

import SharedParser;

options {
    tokenVocab=LexemeBundleLexer;
}

lexemes: lexeme+;

lexeme: conceptId persistentGrammemes? WORD_FORMS_START wordForms WORD_FORMS_END;

persistentGrammemes: grammaticalMeaning;

wordForms: wordForm+;

wordForm: WORD_FORM grammaticalMeaning?;

grammaticalMeaning: LT grammemes GT;
