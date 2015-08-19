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
 * Placeholder that picks its lexeme from {@link ActualArguments} passed to its
 * {@link Template}.
 * <p/>
 * Doesn't capitalize spelling, doesn't have any grammatical meaning.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class PhFromArgument implements Placeholder {
    /**
     * Name of the argument to pick a lexeme from.
     */
    private final transient ArgumentName name;

    /**
     * Ctor.
     * @param name Name of the argument to pick a lexeme from.
     */
    PhFromArgument(final ArgumentName name) {
        this.name = name;
    }

    @Override
    public Lexeme pickLexeme(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return arguments.byName(name);
    }

    @Override
    public ImmutableSet<Grammeme> grammaticalMeaning(
        final ActualArguments arguments
    ) {
        return ImmutableSet.of();
    }

    @Override
    public Spelling capitalize(final Spelling spelling) {
        return spelling;
    }
}
