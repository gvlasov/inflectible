parser grammar LexemeParser;

import SharedParser;

options {
    tokenVocab=LexemeLexer;
}

lexemes: lexeme+;

lexeme: conceptId persistentGrammemes? WORD_FORMS_START wordForms WORD_FORMS_END;

persistentGrammemes: grammaticalMeaning;

wordForms: headword? inflectedWordForm*;

headword: spelling;

inflectedWordForm: spelling grammaticalMeaning;

spelling: SPELLING;

grammaticalMeaning: LT grammemes GT;
