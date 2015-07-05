package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * Grammatical meaning of a word. Grammatical meaning is independent from a
 * lexical meaning. Words with the same grammatical meaning are grammatically
 * interchangeable.
 * <p>For example words <i>dogs</i>, <i>cats</i> and
 * <i>muffins</i> are grammatically interchangeable in the phrase "I like ____".
 * But word "usually" is not grammatically interchangeable with those words.
 * <p>Two words have the same grammatical meaning iff they have the same
 * grammemes.
 * @see <a href="http://en.wikipedia.org/Denotation">Denotation</a> for a
 * lexical meaning.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface GrammaticalMeaning {
    ImmutableSet<Grammeme> grammemes();
}
