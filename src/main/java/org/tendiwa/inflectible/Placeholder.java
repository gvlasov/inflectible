package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;

/**
 * A piece of {@link TextTemplate} that holds place for a word form.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Placeholder {
    /**
     * Fills out this placeholder with a word form.
     * @param arguments Actual arguments of a TextTemplate
     * @param vocabulary Vocabulary with lexemes
     * @return Spelling of a word form
     */
    String fillUp(
        ImmutableMap<String, Lexeme> arguments,
        ImmutableMap<String, Lexeme> vocabulary
    );
}
