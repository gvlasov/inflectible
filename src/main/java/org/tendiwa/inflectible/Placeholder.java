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
 * A piece of {@link Template} that holds place for a word form.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Placeholder {
    /**
     * Picks a lexeme to be used in place of this placeholder when a
     * {@link Template} is being filled out.
     * @param arguments Arguments passed to {@link Template}
     * @param vocabulary Vocabulary
     * @return Lexeme to be used in place of this placeholder
     * @throws Exception If couldn't obtain the lexeme
     */
    Lexeme pickLexeme(ActualArguments arguments, Vocabulary vocabulary)
        throws Exception;

    /**
     * Returns grammatical meaning of the word form to be used in place of
     * this placeholder when {@link Template} is being filled out.
     * @param arguments Arguments passed to {@link Template}
     * @return Grammatical meaning of the word form to be used in place of
     *  this placeholder.
     * @throws Exception If couldn't obtain grammatical meaning
     */
    ImmutableSet<Grammeme> grammaticalMeaning(ActualArguments arguments)
        throws Exception;

    /**
     * Capitalized or leaves untouched the spelling of the word form to be used
     * in place of this placeholder.
     * @param spelling Spelling of the word form to be used in place of this
     *  placeholder
     * @return Spelling with possibly capitalized first letter.
     */
    Spelling capitalize(Spelling spelling);
}
