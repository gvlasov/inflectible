package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * The agreement relation present in actual natural languages.
 * @see Agreement#NONE For Agreement that does nothing and is not present in
 * actual natural languages.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ArgumentAgreement implements Agreement {
    /**
     * Name of the argument to agree to.
     */
    private final String name;

    /**
     * Ctor.
     * @param argument Name of the argument to agree to.
     */
    ArgumentAgreement(String argument) {
        this.name = argument;
    }

    @Override
    public ImmutableSet<Grammeme> grammemes(Map<String, Lexeme> arguments) {
        return arguments
            .get(this.name)
            .persistentGrammemes();
    }
}
