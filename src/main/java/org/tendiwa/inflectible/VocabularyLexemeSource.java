package org.tendiwa.inflectible;

import java.util.Map;

/**
 * Constant {@link LexemeSource}. Picks a lexeme from vocabulary.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class VocabularyLexemeSource implements LexemeSource {
    /**
     * Identifier of a lexeme.
     */
    private final String lexeme;

    /**
     * Ctor.
     * @param lexeme Identifier of a lexeme
     */
    VocabularyLexemeSource(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public Lexeme lexeme(
        Map<String, Lexeme> arguments,
        Map<String, Lexeme> vocabulary
    ) {
        return vocabulary.get(this.lexeme);
    }
}
