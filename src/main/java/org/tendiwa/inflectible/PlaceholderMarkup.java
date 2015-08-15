package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;

import java.util.Optional;

/**
 * Placeholder in place of a lexeme in a marked up text.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface PlaceholderMarkup {
    /**
     * @return Argument's declared name in the first pair of brackets.
     */
    String argumentName();

    /**
     * @return Another argument's declared name in the second pair of
     * brackets. May be missing even if there is the second pair of brackets.
     */
    Optional<String> agreementId();

    /**
     * @return Grammemes names list in the second pair of brackets. If there is
     * no second pair of brackets, it will be empty.
     */
    ImmutableList<String> explicitGrammemes();

    default boolean isCapitalized() {
        return Character.isUpperCase(this.argumentName().charAt(0));
    }
}
