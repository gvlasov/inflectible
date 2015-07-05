parser grammar TextBundleParser;

options {
    tokenVocab=TextBundleLexer;
}

text_bundle: text+;

text: text_id LPAREN arguments RPAREN TEMPLATE_START template TEMPLATE_END;

text_id: ID (DOT ID)*;

arguments: (ID (COMMA ID)*)?;

template: line*;

line: ((COMMENT) | (piece+));

piece: no_category_placeholder
    | placeholder
    | raw_text;

raw_text: (~(PLACEHOLDER_START | TEMPLATE_INDENT | TEMPLATE_END))+;

no_category_placeholder: PLACEHOLDER_START CAPITALIZABLE_ID NO_CATEGORY_PLACEHOLDER_END;

placeholder: PLACEHOLDER_START CAPITALIZABLE_ID CATEGORIES_TRANSITION CATEGORY* agreement? PLACEHOLDER_END;

agreement: AGREEMENT_DELIMITER AGREEMENT_ID;
