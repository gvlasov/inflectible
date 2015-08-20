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
import java.util.Locale;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link TemplateBodyPiece} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedTwoPartVariableConceptPlaceholder implements Placeholder {
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
    public Lexeme pickLexeme(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return this.delegate().pickLexeme(arguments, vocabulary);
    }

    @Override
    public ImmutableSet<Grammeme> grammaticalMeaning(
        final ActualArguments arguments
    ) throws Exception {
        return this.delegate().grammaticalMeaning(arguments);
    }

    @Override
    public Spelling capitalize(final Spelling spelling) {
        return this.delegate().capitalize(spelling);
    }

    /**
     * Creates the placeholder from ANTLR parse tree.
     * @return Placeholder
     */
    private Placeholder delegate() {
        return
            this.withCapitalization(
                this.withAgreement(
                    this.withStaticGrammaticalMeaning(
                        this.lexemeSource()
                    )
                )
            );
    }

    /**
     * Adds agreement to the placeholder if markup says so.
     * @return Placeholder decorated with {@link PhWithAgreement}, or the same
     *  placeholder if markup doesn't declare agreement here.
     */
    private Placeholder withAgreement(final Placeholder placeholder) {
        final Placeholder modified;
        if (this.hasAgreement()) {
            modified = new PhWithAgreement(
                new ArgumentName(
                    this.ctx.agreement().AGREEMENT_ID().getText()
                ),
                placeholder
            );
        } else {
            modified = placeholder;
        }
        return modified;
    }

    /**
     * Checks if this placeholder has agreement with some argument or not.
     * @return True iff this placeholder has agreement with some argument
     */
    private boolean hasAgreement() {
        return this.ctx.agreement() == null;
    }

    /**
     * Adds capitalization to the placeholder if markup says so.
     * @return Placeholder decorated with {@link PhWithCapitalization}, or the
     *  same placeholder if markup doesn't declare capitalization here.
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
     * Adds static grammatical meaning to the placeholder if markup says so.
     * @return Placeholder decorated with {@link PhWithGrammemes}, or the
     *  same placeholder if markup doesn't declare adding static grammatical
     *  meaning here
     */
    private Placeholder withStaticGrammaticalMeaning(
        final Placeholder placeholder
    ) {
        final Placeholder modified;
        if (this.grammemes().isEmpty()) {
            modified = placeholder;
        } else {
            modified = new PhWithGrammemes(this.grammemes(), placeholder);
        }
        return modified;
    }

    /**
     * Checks if this placeholder capitalizes the resulting spelling.
     * @return True iff this placeholder capitalizes the resulting spelling
     */
    private boolean capitalizes() {
        return Character.isUpperCase(this.argumentIdentifier().charAt(0));
    }

    /**
     * Creates a placeholder that picks lexeme from the source declared in
     * the markup.
     * @return Placeholder that knows where to pick its lexeme
     */
    private Placeholder lexemeSource() {
        return new PhFromArgument(
            new ArgumentName(
                this.argumentIdentifier().toLowerCase(Locale.getDefault())
            )
        );
    }

    /**
     * Obtains grammemes of this placeholder from an ANTLR parse tree.
     * @return Grammemes of this placeholder.
     */
    private ImmutableSet<Grammeme> grammemes() {
        return this.ctx.GRAMMEME()
            .stream()
            .map(ParseTree::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }

    /**
     * Obtains the name of the argument that holds a lexeme to fill out
     * this placeholder, with its first letter probably capital.
     * @return Name of an argument, with its first letter probably capital.
     */
    private String argumentIdentifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
