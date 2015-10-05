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
package org.tendiwa.inflectible.implementations;

import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.Spelling;

/**
 * A temporary solution to denote that some generating a lexeme for some part
 * of speech is not implemented yet.
 * @see org.tendiwa.inflectible.PartOfSpeech#lexeme(Spelling, GrammaticalMeaning)
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
final class NotImplementedLexeme implements Lexeme {
    /**
     * Error message.
     */
    private static final String MESSAGE = "Lexeme not implemented";

    @Override
    public Spelling defaultSpelling() throws Exception {
        throw new UnsupportedOperationException(NotImplementedLexeme.MESSAGE);
    }

    @Override
    public Spelling wordForm(
        final GrammaticalMeaning grammemes
    ) throws Exception {
        throw new UnsupportedOperationException(NotImplementedLexeme.MESSAGE);
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() throws Exception {
        throw new UnsupportedOperationException(NotImplementedLexeme.MESSAGE);
    }
}
