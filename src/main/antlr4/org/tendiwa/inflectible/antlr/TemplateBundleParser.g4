parser grammar TemplateBundleParser;

options {
    tokenVocab=TemplateBundleLexer;
}

templates: template+;

template: id LPAREN declaredArguments RPAREN TEMPLATE_START templateBody TEMPLATE_END;

id: ID (DOT ID)*;

declaredArguments: (ID (COMMA ID)*)?;

templateBody: line*;

line: ((COMMENT) | (piece+));

piece: singlePartPlaceholder
    | twoPartPlaceholder
    | vocabularyPlaceholder
    | rawText;

rawText: (~(PLACEHOLDER_START | TEMPLATE_INDENT | TEMPLATE_END))+;

singlePartPlaceholder: PLACEHOLDER_START CAPITALIZABLE_ID NO_GRAMMEME_PLACEHOLDER_END;

twoPartPlaceholder: PLACEHOLDER_START CAPITALIZABLE_ID GRAMMEMES_TRANSITION GRAMMEME* agreement? PLACEHOLDER_END;

vocabularyPlaceholder: PLACEHOLDER_START vocabularyPointer GRAMMEMES_TRANSITION GRAMMEME* agreement PLACEHOLDER_END;

vocabularyPointer: KEYWORD_LEXEME LEXEME_NAME;

agreement: AGREEMENT_DELIMITER AGREEMENT_ID;
