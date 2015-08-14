package org.tendiwa.lexeme;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ArgumentsLexemeSource implements LexemeSource {
    private String argumentName;

    ArgumentsLexemeSource(String argumentName) {
        this.argumentName = argumentName;
    }

    @Override
    public Lexeme lexeme(
        Map<String, Lexeme> arguments,
        Map<String, Lexeme> vocabulary
    ) {
        return arguments.get(this.argumentName);
    }
}
