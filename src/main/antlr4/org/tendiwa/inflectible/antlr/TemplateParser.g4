parser grammar TemplateParser;

import SharedParser;

options {
    tokenVocab=TemplateLexer;
}

templates: template+;

template: id LPAREN declaredArguments RPAREN TEMPLATE_START templateBody TEMPLATE_END;

id: ID (DOT ID)*;

declaredArguments: (argumentName (COMMA argumentName)*)?;

templateBody: line*;

line: ((COMMENT) | (piece+));

piece: singlePartPlaceholder
    | twoPartPlaceholder
    | vocabularyPlaceholder
    | rawText;

rawText: (~(PLACEHOLDER_START | TEMPLATE_INDENT | TEMPLATE_END))+;

singlePartPlaceholder: PLACEHOLDER_START capitalizableArgumentName NO_GRAMMEME_PLACEHOLDER_END;

twoPartPlaceholder: PLACEHOLDER_START capitalizableArgumentName GRAMMEMES_TRANSITION grammemes? agreement? PLACEHOLDER_END;

vocabularyPlaceholder: PLACEHOLDER_START vocabularyPointer GRAMMEMES_TRANSITION grammemes? agreement PLACEHOLDER_END;

vocabularyPointer: keywordLexeme conceptId;

capitalizableArgumentName: argumentName | capitalizedArgumentName;

keywordLexeme: KEYWORD_LEXEME | CAPITALIZED_KEYWORD_LEXEME;

argumentName: ARGUMENT_NAME;

capitalizedArgumentName: CAPITALIZED_ARGUMENT_NAME;

agreement: AGREEMENT_DELIMITER argumentName;
