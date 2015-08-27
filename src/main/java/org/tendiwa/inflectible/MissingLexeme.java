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
 * Fake lexeme that consists of a single word form whose spelling is just an
 * error message.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class MissingLexeme implements Lexeme {
    /**
     * Language of the fake lexeme.
     */
    private final transient Language language;

    /**
     * Concept that is missing a lexeme.
     */
    private final transient Concept concept;

    /**
     * Ctor.
     * @param conc Concept that is missing a lexeme
     * @param lang Language of the fake lexeme
     */
    public MissingLexeme(final Concept conc, final Language lang) {
        this.language = lang;
        this.concept = conc;
    }

    @Override
    public Spelling defaultSpelling() throws Exception {
        return this.delegate().defaultSpelling();
    }

    @Override
    public Spelling wordForm(
        final GrammaticalMeaning grammemes
    ) throws Exception {
        return this.delegate().wordForm(grammemes);
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() throws Exception {
        return this.delegate().persistentGrammemes();
    }

    /**
     * Creates a lexeme with a single word form whose spelling consists of
     * the error message.
     * @return Lexeme with a single word form whose spelling consists of the
     *  error message.
     * @throws Exception If the Concept identifier is not valid
     */
    private Lexeme delegate() throws Exception {
        return new SingleFormLexeme(
            String.format(
                this.language.missingLexemeFormat(),
                this.concept.identifier()
            )
        );
    }
}
