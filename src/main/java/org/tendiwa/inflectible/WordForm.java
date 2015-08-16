package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;

/**
 * A word form is what {@link Lexeme}s contain of. All word forms of the same
 * lexeme refer to the same conception, but have different <i>grammatical
 * meaning</i> and, consecutively, <i>spelling</i>. Grammatical meaning is a
 * set of {@link Grammeme}s, and spelling is just how this particular word
 * form is written.
 * <p/>
 * <a href="https://en.wikipedia.org/wiki/Morphology_(linguistics)#Lexemes_and_word_forms">
 *     Word forms on Wikipedia</a>
 * <p/>
 * For example, <i>choose</i> and <i>chosen</i> are two word forms of a lexeme
 * CHOOSE. They have different grammatical meaning: one is an infinitive
 * form, the other is a perfect form.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface WordForm {
    /**
     * How a word form is spelled.
     * @return Spelling.
     */
    String spelling();

    /**
     * How similar is this word form's grammatical meaning to another
     * grammatical meaning.
     * @param grammemes Another grammatical meaning.
     * @return Size of the intersection set of this word form's grammatical
     * meaning and
     */
    int similarity(ImmutableSet<Grammeme> grammemes);
}
