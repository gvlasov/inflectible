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

import java.util.List;
import org.tenidwa.collections.utils.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * Text created by filling out a {@link Template}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class FilledOutText implements Text {
    /**
     * Template to fill out.
     */
    private final transient Template template;

    /**
     * Vocabulary to search for lexemes in.
     */
    private final transient Vocabulary vocabulary;

    /**
     * Arguments to fill out the template with.
     */
    private final transient List<Conception> arguments;

    /**
     * Ctor.
     * @param text Template to fill out
     * @param lexemes Vocabulary to search for lexemes in
     * @param conceptions Arguments to fill out the template with
     */
    public FilledOutText(
        final Template text,
        final Vocabulary lexemes,
        final List<Conception> conceptions
    ) {
        this.template = text;
        this.vocabulary = lexemes;
        this.arguments = conceptions;
    }

    @Override
    public String string() throws Exception {
        return this.template.fillUp(
            this.arguments
                .stream()
                .map(
                    Rethrowing.rethrowFunction(
                        conception
                            -> this.vocabulary
                            .lexeme(
                                new ValidatedConception(
                                    conception.identifier()
                                )
                            )
                    )
                )
                .collect(Collectors.toImmutableList()),
            this.vocabulary
        );
    }
}
