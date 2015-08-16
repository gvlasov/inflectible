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

import com.google.common.collect.ImmutableSet;

/**
 * A word form is what {@link Lexeme}s contain of. All word forms of the same
 * lexeme refer to the same conception, but have different <i>grammatical
 * meaning</i> and, consecutively, <i>spelling</i>. Grammatical meaning is a
 * set of {@link Grammeme}s, and spelling is just how this particular word
 * form is written.
 * <p/>
 * <a href="
 * https://en.wikipedia.org/wiki/Morphology_(linguistics)#Lexemes_and_word_forms
 * ">Word forms on Wikipedia</a>
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
     *  meaning and the other grammatical meaning.
     */
    int similarity(ImmutableSet<Grammeme> grammemes);
}
