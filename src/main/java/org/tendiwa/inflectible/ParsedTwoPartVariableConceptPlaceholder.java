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
import java.util.Optional;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link Placeholder} parsed from an ANTLR parse tree.
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
        return this.delegate().fillUp(arguments, vocabulary);
    }

    /**
     * Creates a template from the
     * {@link ParsedTwoPartVariableConceptPlaceholder#ctx}.
     * @return Placeholder
     */
    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            this.grammemes(),
            this.agreement()
        );
    }

    /**
     * Finds out the kind of agreement used in this placeholder.
     * @return If there is no agreement, then {@link Agreement#NONE}, else
     *  {@link ArgumentAgreement} if there is agreement.
     */
    private Agreement agreement() {
        final Optional<String> agreementName = this.agreementArgumentName();
        final Agreement answer;
        if (agreementName.isPresent()) {
            answer = new ArgumentAgreement(
                new ArgumentName(
                    agreementName.get()
                )
            );
        } else {
            answer = Agreement.NONE;
        }
        return answer;
    }

    /**
     * Determines capitalization of this placeholder.
     * @return Capitalization to be applied to this placeholder.
     */
    private Capitalization capitalization() {
        final Capitalization capitalize;
        if (Character.isUpperCase(this.argumentName().charAt(0))) {
            capitalize = Capitalization.CAPITALIZE;
        } else {
            capitalize = Capitalization.IDENTITY;
        }
        return capitalize;
    }

    /**
     * Creates a {@link LexemeSource} for this placeholder.
     * @return A lexeme source.
     */
    private LexemeSource lexemeSource() {
        return new ArgumentsLexemeSource(
            new ArgumentName(
                this.argumentName().toLowerCase(Locale.getDefault())
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
     * Obtains the name of the argument this placeholder agrees with.
     * @return Name of the argument to agree with, or empty if none is
     *  specified.
     */
    private Optional<String> agreementArgumentName() {
        final Optional<String> answer;
        if (this.ctx.agreement() == null) {
            answer = Optional.empty();
        } else {
            answer = Optional.of(this.ctx.agreement().AGREEMENT_ID().getText());
        }
        return answer;
    }

    /**
     * Obtains the name of the argument that holds a lexeme to fill out this
     * placeholder.
     * @return Name of an argument.
     */
    private String argumentName() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
