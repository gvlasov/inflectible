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
 * Language that creates its {@link Grammar} from an enum of {@link Grammeme}s.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public abstract class AbstractLanguage implements Language {

    /**
     * Enum that contains grammemes for this language's
     * {@link Language#grammar()}.
     */
    private final transient Class<? extends Grammeme> grammemes;

    /**
     * Enum that contains parts of speech for this language's
     * {@link Language#grammar()}.
     */
    private final transient Class<? extends PartOfSpeech> parts;

    /**
     * Ctor.
     * @param grams Enum that contains grammemes for this language's
     *  {@link Language#grammar()}.
     * @param prts Enum that contains parts of speech for this language's
     *  {@link Language#grammar()}.
     */
    protected AbstractLanguage(
        final Class<? extends Grammeme> grams,
        final Class<? extends PartOfSpeech> prts

    ) {
        this.grammemes = grams;
        this.parts = prts;
    }

    @Override
    public final Grammar grammar() {
        return new EnumBasedGrammar(this.grammemes, this.parts);
    }
}
