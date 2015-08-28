parser grammar LexemeParser;

import SharedParser;

options {
    tokenVocab=LexemeLexer;
}

lexemes: lexeme+;

lexeme: conceptId partOfSpeech persistentGrammemes? WORD_FORMS_START wordForms WORD_FORMS_END;

persistentGrammemes: grammaticalMeaning;

partOfSpeech: LPAREN PART_OF_SPEECH RPAREN;

wordForms: headword? inflectedWordForm*;

headword: spelling;

inflectedWordForm: spelling grammaticalMeaning;

spelling: SPELLING;

grammaticalMeaning: LT grammemes GT;
