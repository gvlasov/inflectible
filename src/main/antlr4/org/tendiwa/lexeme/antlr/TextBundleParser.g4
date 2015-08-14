parser grammar TextBundleParser;

options {
    tokenVocab=TextBundleLexer;
}

textTemplates: textTemplate+;

textTemplate: textId LPAREN declaredArguments RPAREN TEMPLATE_START templateBody TEMPLATE_END;

textId: ID (DOT ID)*;

declaredArguments: (ID (COMMA ID)*)?;

templateBody: line*;

line: ((COMMENT) | (piece+));

piece: singlePartPlaceholder
    | twoPartPlaceholder
    | rawText;

rawText: (~(PLACEHOLDER_START | TEMPLATE_INDENT | TEMPLATE_END))+;

singlePartPlaceholder: PLACEHOLDER_START CAPITALIZABLE_ID NO_GRAMMEME_PLACEHOLDER_END;

twoPartPlaceholder: PLACEHOLDER_START CAPITALIZABLE_ID GRAMMEMES_TRANSITION GRAMMEME* agreement? PLACEHOLDER_END;

agreement: AGREEMENT_DELIMITER AGREEMENT_ID;
