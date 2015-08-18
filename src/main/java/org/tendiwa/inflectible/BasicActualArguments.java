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

/**
 * {@link ActualArguments} defined by a list of declared arguments' names and
 * actual values of those arguments.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicActualArguments implements ActualArguments {
    /**
     * Declared names of arguments.
     */
    private final transient ImmutableList<String> declared;

    /**
     * Actual values of arguments.
     */
    private final transient ImmutableList<Lexeme> values;

    /**
     * Ctor.
     * @param names Declared names of arguments
     * @param arguments Actual values of arguments
     */
    BasicActualArguments(
        final ImmutableList<String> names,
        final ImmutableList<Lexeme> arguments
    ) {
        this.declared = names;
        this.values = arguments;
    }

    /**
     * Ctor for empty map of arguments.
     */
    BasicActualArguments() {
        this(ImmutableList.of(), ImmutableList.of());
    }

    @Override
    public Lexeme byName(final ArgumentName name) throws Exception {
        return this.values.get(this.declared.indexOf(name.string()));
    }
}
