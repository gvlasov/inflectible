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
import com.google.common.collect.Sets;

/**
 * A {@link WordForm} defined by its spelling and grammatical meaning.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicWordForm implements WordForm {
    /**
     * Spelling of this word.
     */
    private final transient String form;

    /**
     * Grammatical meaning of this word.
     */
    private final transient ImmutableSet<Grammeme> grammemes;

    /**
     * Ctor.
     * @param word Spelling of this word
     * @param meaning Grammatical meaning of this word
     */
    public BasicWordForm(
        final String word,
        final ImmutableSet<Grammeme> meaning
    ) {
        this.form = word;
        this.grammemes = meaning;
    }

    @Override
    public String spelling() {
        return this.form;
    }

    @Override
    public int similarity(final ImmutableSet<Grammeme> meaning) {
        return Sets.intersection(
            meaning,
            this.grammemes
        ).size();
    }
}
