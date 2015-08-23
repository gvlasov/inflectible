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
 * {@link GrammarRule} that states that content of a placeholder must
 * <a href="https://en.wikipedia.org/wiki/Agreement_(linguistics)">agree</a>
 * with an argument lexeme.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GrAgreement implements GrammarRule {
    /**
     * ANTLR parse tree of an agreement directive, or empty.
     */
    private final transient Optional<TemplateBundleParser.AgreementContext>
        ctx;

    /**
     * GrammarRule to decorate.
     */
    private final transient GrammarRule decorated;

    /**
     * Ctor.
     * @param context ANTLR parse tree of an agreement directive, or empty.
     * @param wrapped Decorated grammar rule. Grammatical meaning from
     * agreement is added to its grammatical meaning.
     */
    GrAgreement(
        final Optional<TemplateBundleParser.AgreementContext> context,
        final GrammarRule wrapped
    ) {
        this.ctx = context;
        this.decorated = wrapped;
    }

    @Override
    public GrammaticalMeaning grammaticalMeaning(
        final ActualArguments arguments
    ) throws Exception {
        final GrammaticalMeaning modified;
        if (this.ctx.isPresent()) {
            modified = new GmCombined(
                this.decorated.grammaticalMeaning(arguments),
                arguments.byName(new AnFromAgreement(this.ctx.get()))
                    .persistentGrammemes()
            );
        } else {
            modified = this.decorated.grammaticalMeaning(arguments);
        }
        return modified;
    }
}
