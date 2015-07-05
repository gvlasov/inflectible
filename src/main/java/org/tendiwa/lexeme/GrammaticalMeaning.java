package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * Grammatical meaning of a word form. Grammatical meaning is independent from a
 * lexical meaning. Word forms with the same grammatical meaning are
 * grammatically interchangeable.
 * <p>For example words <i>dogs</i>, <i>cats</i> and
 * <i>muffins</i> are grammatically interchangeable in the phrase "I like
 * those ____". But lexeme "usually" is not grammatically interchangeable with
 * those words.
 * <p>Two words have the same grammatical meaning iff they have the same
 * grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface GrammaticalMeaning {
    /**
     * @return Set of units of grammatical meaning.
     */
    ImmutableSet<Grammeme> grammemes();
}
