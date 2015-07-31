package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.tendiwa.rocollections.ReadOnlySet;

/**
 * Grammatical meaning of a word form. Grammatical meaning is independent from a
 * lexical meaning. Word forms with the same grammatical meaning are
 * grammatically interchangeable.
 * <p>For example words forms <i>dogs</i>, <i>cats</i> and
 * <i>muffins</i> are grammatically interchangeable in the phrase "I like
 * those ____". But word form "usually" is not grammatically interchangeable
 * with those word forms.
 * <p>Two word forms have the same grammatical meaning iff they have the same
 * grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface GrammaticalMeaning extends ReadOnlySet<Grammeme> {
    /**
     * @param meaning Another meaning
     * @return How many grammemes this meaning shares with another meaning.
     */
    default int similarity(GrammaticalMeaning meaning) {
        return Sets.intersection(
            ImmutableSet.copyOf(this),
            ImmutableSet.copyOf(meaning)
        ).size();
    }
}
