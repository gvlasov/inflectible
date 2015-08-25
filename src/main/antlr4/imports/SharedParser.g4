parser grammar GrammemesParser;

options {
    tokenVocab=GrammemesLexer;
}

grammemes: GRAMMEME+;

conceptId: CONCEPT_ID;
