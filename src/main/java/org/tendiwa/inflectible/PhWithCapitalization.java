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
 * Placeholder that capitalizes spelling of the resulting word form.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class PhWithCapitalization implements Placeholder {

    /**
     * Placeholder to decorate.
     */
    private final Placeholder decorated;

    /**
     * Ctor.
     * @param wrapped Placeholder to decorate
     */
    PhWithCapitalization(final Placeholder wrapped) {
        this.decorated = wrapped;
    }

    @Override
    public Lexeme pickLexeme(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return this.decorated.pickLexeme(arguments, vocabulary);
    }

    @Override
    public ImmutableSet<Grammeme> grammaticalMeaning(
        final ActualArguments arguments
    ) throws Exception {
        return this.decorated.grammaticalMeaning(arguments);
    }

    @Override
    public Spelling capitalize(final Spelling spelling) {
        return new CapitalizedSpelling(spelling);
    }
}
