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

import java.util.Optional;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * {@link TemplateBodyPiece} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedTwoPartVariableConceptPlaceholder
    implements TemplateBodyPiece {
    /**
     * Grammar of the language of this placeholder.
     */
    private final transient Grammar grammar;

    /**
     *
     * ANTLR parse tree of a placeholder.
     */
    private final transient TemplateBundleParser.TwoPartPlaceholderContext ctx;

    /**
     * Ctor.
     * @param rules Grammar of a language of a template this placeholder
     *  comes from.
     * @param context ANTLR parse tree of a placeholder.
     */
    ParsedTwoPartVariableConceptPlaceholder(
        final Grammar rules,
        final TemplateBundleParser.TwoPartPlaceholderContext context
    ) {
        super();
        this.grammar = rules;
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return new Placeholder(
            new LrFromArgument(
                new AnParsed(this.ctx.argumentName())
            ),
            new GrAgreement(
                Optional.ofNullable(this.ctx.agreement()),
                new GrStatic(
                    new GmOfParsedPlaceholder(
                        this.grammar,
                        this.ctx.grammemes()
                    )
                )
            ),
            new SrParsedArgumentCapitalization(this.ctx.argumentName())
        )
            .fillUp(arguments, vocabulary);
    }
}
