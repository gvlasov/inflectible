package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;

/**
 * Collection of word forms belonging to the same conception, and their
 * persistent grammatical meaning.
 * @see <a href="https://en.wikipedia.org/wiki/Lexeme">Lexeme article on
 *  Wikipedia</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Lexeme {
    /**
     * The word form that could go to a dictionary.
     * <p>E.g. infinitive for a verb in English, or singular form in
     * nominative case for a noun in Russian.
     * @return The default spelling of this lexeme.
     */
    String defaultSpelling();

    /**
     * @param grammaticalMeaning Grammatical meaning
     * @return The word form of this lexeme with its grammatical meaning
     * closest to {@code grammaticalMeaning}
     */
    String wordForm(ImmutableSet<Grammeme> grammaticalMeaning);

    /**
     * Grammemes inherent to this lexeme.
     * <p>For example, in Russian language, a word <i>кошка</i> (a cat)
     * inherently contains Feminine grammeme from the grammatical category of
     * gender. No matter the word form of <i>кошка</i>, all of them will
     * contain Feminine grammeme (Russian.Grammemes.Жен)
     */
   ImmutableSet<Grammeme> persistentGrammemes();
}
