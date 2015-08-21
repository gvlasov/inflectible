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

import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * Placeholder that gets a lexeme from a vocabulary instead of from an argument.
 * @see ParsedSinglePartPlaceholder
 * @see ParsedTwoPartVariableConceptPlaceholder
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class ParsedVocabularyPlaceholder
    extends AbstractDelegatingPlaceholder {
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
    public Placeholder delegate() {
        return this.withCapitalization(
            this.withAgreement(
                this.withGrammemes(
                    new PhFromVocabulary(
                        new LexemeName(
                            this.ctx.vocabularyPointer()
                        )
                    )
                )
            )
        );
    }

    /**
     * Creates placeholder with capitalization.
     * @param placeholder Placeholder
     * @return Placeholder with caiptalization
     */
    private Placeholder withCapitalization(final Placeholder placeholder) {
        final Placeholder modified;
        if (this.capitalizes()) {
            modified = new PhWithCapitalization(placeholder);
        } else {
            modified = placeholder;
        }
        return modified;
    }

    /**
     * Checks if placeholder should capitalize spelling.
     * @return True iff placeholder should capitalize
     */
    private boolean capitalizes() {
        return Character.isUpperCase(
            this.ctx.vocabularyPointer().KEYWORD_LEXEME().getText().charAt(0)
        );
    }

    /**
     * Creates placeholder with agreement.
     * @param placeholder Placeholder
     * @return Placeholder with agreement
     */
    private Placeholder withAgreement(final Placeholder placeholder) {
        return new PhWithAgreement(
            this.agreementArgumentName(),
            placeholder
        );
    }

    /**
     * Returns argument name.
     * @return Argument name
     */
    private ArgumentName agreementArgumentName() {
        return new ArgumentName(
            this.ctx.agreement().AGREEMENT_ID().getText()
        );
    }

    /**
     * Creates placeholder with grammemes.
     * @param placeholder Placeholder
     * @return Decorated placeholder
     */
    private Placeholder withGrammemes(final Placeholder placeholder) {
        return new PhWithGrammemes(
            this.ctx.GRAMMEME().stream()
                .map(TerminalNode::getText)
                .map(this.grammar::grammemeByName)
                .collect(Collectors.toImmutableSet()),
            placeholder
        );
    }

}
