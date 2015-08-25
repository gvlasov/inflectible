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
import java.util.List;
import java.util.stream.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * {@link Template} defined by its arguments' names and a
 * heterogeneous list of its parts (placeholders and plain text chunks).
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicTemplate implements Template {
    /**
     * Argument names.
     */
    private final transient ImmutableList<ArgumentName> arguments;
    /**
     * Heterogeneous list of template's parts (placeholders and plain text
     * chunks).
     */
    private final transient List<TemplateBodyPiece> parts;

    /**
     * Ctor.
     * @param names Argument names
     * @param pieces List of template's parts (placeholders and plain text
     *  chunks)
     */
    public BasicTemplate(
        final ImmutableList<ArgumentName> names,
        final List<TemplateBodyPiece> pieces
    ) {
        this.arguments = names;
        this.parts = pieces;
    }

    @Override
    public String fillUp(
        final ImmutableList<Lexeme> lexemes,
        final Vocabulary vocabulary
    ) throws Exception {
        final ActualArguments actualArguments =
            new BasicActualArguments(this.arguments, lexemes);
        return this.parts.stream()
            .map(
                Rethrowing.rethrowFunction(
                    part -> part.fillUp(actualArguments, vocabulary)
                )
            )
            .collect(Collectors.joining());
    }
}
