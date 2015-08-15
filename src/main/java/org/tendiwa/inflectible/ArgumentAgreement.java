package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ArgumentAgreement implements Agreement {

    private final String argumentName;

    ArgumentAgreement(String argumentName) {
        this.argumentName = argumentName;
    }

    @Override
    public ImmutableSet<Grammeme> grammemes(Map<String, Lexeme> arguments) {
        return arguments
            .get(this.argumentName)
            .persistentGrammemes();
    }
}
