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

piece: base_form_placeholder
    | placeholder
    | raw_text;

raw_text: (~(PLACEHOLDER_START | TEMPLATE_INDENT | TEMPLATE_END))+;

base_form_placeholder: PLACEHOLDER_START CAPITALIZABLE_ID NO_GRAMMEME_PLACEHOLDER_END;

placeholder: PLACEHOLDER_START CAPITALIZABLE_ID GRAMMEMES_TRANSITION GRAMMEME* agreement? PLACEHOLDER_END;

agreement: AGREEMENT_DELIMITER AGREEMENT_ID;
