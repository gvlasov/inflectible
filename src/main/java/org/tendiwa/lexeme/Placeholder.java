package org.tendiwa.lexeme;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * Placeholder in place of a lexeme in a marked up text.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Placeholder {
    /**
     * @return Argument declared name in the first pair of brackets.
     *
     */
    String id();

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
}
