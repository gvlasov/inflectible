parser grammar WordBundleParser;

options {
    tokenVocab=WordBundleLexer;
}

word_bundle: word+;

word: conception persistent_grammemes? LEXEMES_START word_forms LEXEMES_END;

conception: OPENING_QUOTE CONCEPTION_ID CLOSING_QUOTE;

persistent_grammemes: grammemes;

word_forms: entry+;

entry: WORD_FORM grammemes?;

grammemes: BRACKET GRAMMEME+ RBRACKET;

