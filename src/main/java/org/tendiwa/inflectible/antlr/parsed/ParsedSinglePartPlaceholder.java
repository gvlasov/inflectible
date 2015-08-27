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
package org.tendiwa.inflectible.antlr.parsed;

import org.tendiwa.inflectible.ActualArguments;
import org.tendiwa.inflectible.GmEmpty;
import org.tendiwa.inflectible.GrStatic;
import org.tendiwa.inflectible.LrFromArgument;
import org.tendiwa.inflectible.Placeholder;
import org.tendiwa.inflectible.TemplateBodyPiece;
import org.tendiwa.inflectible.Vocabulary;
import org.tendiwa.inflectible.antlr.TemplateParser;

/**
 * {@link TemplateBodyPiece} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class ParsedSinglePartPlaceholder implements TemplateBodyPiece {
    /**
     * ANTLR parse tree of a placeholder.
     */
    private final transient TemplateParser.SinglePartPlaceholderContext
        ctx;

    /**
     * Ctor.
     * @param context ANTLR parse tree of a two-part placeholder.
     */
    ParsedSinglePartPlaceholder(
        final TemplateParser.SinglePartPlaceholderContext context
    ) {
        super();
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return new Placeholder(
            new LrFromArgument(
                new AnParsedCapitalizable(
                    this.ctx.capitalizableArgumentName()
                )
            ),
            new GrStatic(
                new GmEmpty()
            ),
            new SrParsedArgumentCapitalization(
                this.ctx.capitalizableArgumentName()
            )
        )
            .fillUp(arguments, vocabulary);
    }
}
