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

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import org.tendiwa.inflectible.ActualArguments;
import org.tendiwa.inflectible.GrCombined;
import org.tendiwa.inflectible.GrStatic;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.GrammarRule;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Grammar rule that produces grammatical meaning by looking at placeholder's
 * agreement and static grammemes. Placeholders may omit declaring
 * agreement or static grammemes, but one of those is always present.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GrParsedStaticOrAgreement implements GrammarRule {
    /**
     * Grammar of a natural language.
     */
    private final transient Grammar grammar;

    /**
     * ANTLR parse tree of grammemes.
     */
    private final transient
        Optional<TemplateBundleParser.GrammemesContext> grammemes;

    /**
     * ANTLR parse tree of agreement.
     */
    private final transient
        Optional<TemplateBundleParser.AgreementContext> agreement;

    /**
     * Ctor.
     * @param gram Grammar of a natural language
     * @param grams ANTLR parse tree of grammemes
     * @param agr ANTLR parse tree of agreement
     */
    public GrParsedStaticOrAgreement(
        final Grammar gram,
        final Optional<TemplateBundleParser.GrammemesContext> grams,
        final Optional<TemplateBundleParser.AgreementContext> agr
    ) {
        this.grammar = gram;
        this.grammemes = grams;
        this.agreement = agr;
    }

    @Override
    public GrammaticalMeaning grammaticalMeaning(
        final ActualArguments arguments
    ) throws Exception {
        return this.upToTwoCombinedRules().grammaticalMeaning(arguments);
    }

    /**
     * Creates a grammar rule that will do what this grammar rule is supposed
     * to do.
     * @return Grammar rule
     */
    private GrammarRule upToTwoCombinedRules() {
        final ImmutableList.Builder<GrammarRule> rules =
            ImmutableList.builder();
        if (this.agreement.isPresent()) {
            rules.add(
                new GrAgreement(
                    this.agreement.get()
                )
            );
        }
        if (this.grammemes.isPresent()) {
            rules.add(
                new GrStatic(
                    new GmOfParsedGrammemes(
                        this.grammar,
                        this.grammemes.get()
                    )
                )
            );
        }
        return new GrCombined(rules.build());
    }
}
