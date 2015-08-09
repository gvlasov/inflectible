package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * Collection of word forms belonging to the same conception.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Lexeme {
    /**
     * The word form that could go to a dictionary.
     * <p>E.g. infinitive for a verb in English, or singular form in
     * nominative case for a noun in Russian.
     * @return The default form of a word.
     */
    String baseForm();

    /**
     * The word form that corresponds to some grammatical meaning.
     * <p>If there are grammemes in {@code meaning} that are not present in any
     * of the word forms in this lexeme, those grammemes are ignored.
     * @param meaning Grammatical meaning.
     * @return The word form that corresponds to some grammatical meaning.
     */
    String form(final ImmutableSet<Grammeme> meaning);

    /**
     * Grammemes inherent to this lexeme.
     * <p>For example, in Russian language, a word <i>кошка</i> (a cat)
     * inherently contains Feminine grammeme from the grammatical category of
     * gender. No matter the word form of <i>кошка</i>, all of them will
     * contain Feminine grammeme (Russian.Grammemes.Жен)
     */
   ImmutableSet<Grammeme> persistentGrammemes();
}
