package org.tendiwa.inflectible;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class VocabularyLexemeSource implements LexemeSource {
    private final String lexemeId;

    VocabularyLexemeSource(String lexemeId) {
        this.lexemeId = lexemeId;
    }

    @Override
    public Lexeme lexeme(
        Map<String, Lexeme> arguments,
        Map<String, Lexeme> vocabulary
    ) {
        return vocabulary.get(this.lexemeId);
    }
}
