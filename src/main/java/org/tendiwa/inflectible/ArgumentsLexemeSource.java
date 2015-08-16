package org.tendiwa.inflectible;

import java.util.Map;

/**
 * {@link LexemeSource} that chooses a lexeme by its identifier from actual
 *  arguments passed to {@link TextTemplate}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ArgumentsLexemeSource implements LexemeSource {
    /**
     * Name of an argument.
     */
    private String name;

    /**
     * Ctor.
     * @param argument Name of an argument.
     */
    ArgumentsLexemeSource(String argument) {
        this.name = argument;
    }

    @Override
    public Lexeme lexeme(
        Map<String, Lexeme> arguments,
        Map<String, Lexeme> vocabulary
    ) {
        return arguments.get(this.name);
    }
}
