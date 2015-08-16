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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Lexeme with only its dictionary form (with default grammatical meaning)
 * and no persistent grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class SingleFormLexeme implements Lexeme {
    /**
     * Spelling of the dictionary word form.
     */
    private final transient String spelling;

    /**
     * Ctor.
     * @param form Spelling of the dictionary word form.
     */
    SingleFormLexeme(final String form) {
        this.spelling = form;
    }

    @Override
    public String defaultSpelling() {
        return this.delegate().defaultSpelling();
    }

    @Override
    public String wordForm(final ImmutableSet<Grammeme> grammemes) {
        return this.delegate().wordForm(grammemes);
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.delegate().persistentGrammemes();
    }

    /**
     * Creates a lexeme to delegate all the interface methods of
     * {@link SingleFormLexeme}.
     * @return Lexeme with a single word form (with default grammatical
     *  meaning) and no persistent grammemes.
     */
    private Lexeme delegate() {
        return new BasicLexeme(
            ImmutableSet.of(),
            ImmutableList.<WordForm>of(
                new BasicWordForm(this.spelling, ImmutableSet.of())
            )
        );
    }
}
