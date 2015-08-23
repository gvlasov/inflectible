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

import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Determines how to caiptalize the content of a {@link Placeholder} whose
 * lexeme is provided via an argument.
 * @see ActualArguments For what are arguments
 * @see SrParsedVocabularyCapitalization
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
final class SrParsedArgumentCapitalization implements SpellingRule {
    /**
     * ANTLR parse tree with an argument name.
     */
    private final transient TemplateBundleParser.ArgumentNameContext ctx;

    /**
     * Ctor.
     * @param context ANTLR parse tree with an argument name
     */
    SrParsedArgumentCapitalization(
        final TemplateBundleParser.ArgumentNameContext context
    ) {
        this.ctx = context;
    }

    @Override
    public Spelling adjustSpelling(final Spelling spelling) {
        return new SrConditionalCapitalization(this::capitalizes)
            .adjustSpelling(spelling);
    }

    /**
     * Checks if ANTLR parse tree tells to capitalize placeholder's content.
     * @return True iff ANTLR parse tree tells to capitalize placeholder's
     *  content.
     */
    private boolean capitalizes() {
        return Character.isUpperCase(
            this.ctx.CAPITALIZABLE_ID().getText().charAt(0)
        );
    }
}
