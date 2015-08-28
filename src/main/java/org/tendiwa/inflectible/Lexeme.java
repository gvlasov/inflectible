/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Georgy Vlasov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tendiwa.inflectible;

/**
 * Various spellings used to refer to the same concept, but in different
 * grammatical contexts. For example, in English, <i>runs</i>, <i>ran</i> and
 * <i>running</i> are two forms of the same lexeme, RUN.
 * <p/>
 * All word forms of the same lexeme refer to the same concept, but have
 * different {@link GrammaticalMeaning} and, probably, different
 * {@link Spelling}.
 * <p/>
 * For example, <i>choose</i> and <i>chosen</i> are two word forms of a lexeme
 * CHOOSE. They have different grammatical meaning: one is an infinitive
 * form, the other is a perfect form.
 * @see <a href="https://en.wikipedia.org/wiki/Lexeme">Lexeme article on
 *  Wikipedia</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public interface Lexeme {
    /**
     * The word form that could go to a dictionary.
     * <p>E.g. infinitive for a verb in English, or singular form in
     * nominative case for a noun in Russian.
     * @return The default spelling of this lexeme.
     * @throws Exception If could not obtain spelling
     */
    Spelling defaultSpelling() throws Exception;

    /**
     * Finds a word form of this lexeme with grammatical meaning closest to
     * the provided one.
     * @param grammemes Grammatical meaning
     * @return The word form of this lexeme with its grammatical meaning
     *  closest to {@code grammemes}
     * @throws Exception If could not obtain word form
     */
    Spelling wordForm(GrammaticalMeaning grammemes) throws Exception;

    /**
     * Grammemes inherent to this lexeme.
     * <p>For example, in Russian language, a word <i>кошка</i> (a cat)
     * inherently contains Feminine grammeme from the grammatical category of
     * gender. No matter the word form of <i>кошка</i>, all of them will
     * contain Feminine grammeme
     * ({@link org.tendiwa.inflectible.implementations.RussianGrammeme#Жен}).
     * @return Persistent grammatical meaning of this lexeme
     * @throws Exception If could not obtain persistent grammemes
     */
    GrammaticalMeaning persistentGrammemes() throws Exception;
}
