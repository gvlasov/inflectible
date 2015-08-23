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

import java.util.Optional;
import org.tendiwa.inflectible.*;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Placeholder that gets a lexeme from a vocabulary instead of from an argument.
 * @see ParsedSinglePartPlaceholder
 * @see ParsedTwoPartVariableConceptPlaceholder
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class ParsedVocabularyPlaceholder implements TemplateBodyPiece {
    /**
     * Grammar of the language of the template of this placeholder.
     */
    private final transient Grammar grammar;

    /**
     * ANTLR parse tree with a vocabulary placeholder's markup.
     */
    private final transient TemplateBundleParser.VocabularyPlaceholderContext
        ctx;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of the template
     * @param context ANTLR parse tree with a vocabulary placeholder's markup
     */
    ParsedVocabularyPlaceholder(
        final Grammar grammemes,
        final TemplateBundleParser.VocabularyPlaceholderContext context
    ) {
        super();
        this.grammar = grammemes;
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return new Placeholder(
            new LrParsedFromVocabulary(
                this.ctx.vocabularyPointer()
            ),
            new GrAgreement(
                Optional.of(this.ctx.agreement()),
                new GrStatic(
                    new GmOfParsedPlaceholder(
                        this.grammar,
                        this.ctx.grammemes()
                    )
                )
            ),
            new SrParsedVocabularyCapitalization(
                this.ctx.vocabularyPointer()
            )
        )
            .fillUp(arguments, vocabulary);
    }
}
