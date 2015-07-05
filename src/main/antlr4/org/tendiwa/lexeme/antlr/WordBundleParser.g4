parser grammar WordBundleParser;

options {
    tokenVocab=WordBundleLexer;
}

word_bundle: word+;

word: denotation persistent_grammemes? LEXEMES_START entry+ LEXEMES_END;

denotation: OPENING_QUOTE DENOTATION_ID CLOSING_QUOTE;

persistent_grammemes: grammemes;

entry: lexeme grammemes?;

lexeme: WORD;

grammemes: LBRACKET GRAMMEME+ RBRACKET;

