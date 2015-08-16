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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * {@link Placeholder} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholder implements Placeholder {
    /**
     * ANTLR parse tree of a placeholder.
     */
    private final transient TemplateBundleParser.SinglePartPlaceholderContext
        ctx;

    /**
     * Ctor.
     * @param context ANTLR parse tree of a two-part placeholder.
     */
    ParsedSinglePartPlaceholder(
        final TemplateBundleParser.SinglePartPlaceholderContext context
    ) {
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ImmutableMap<String, Lexeme> arguments,
        final ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.delegate().fillUp(arguments, vocabulary);
    }

    /**
     * Creates a placeholder with aspects obtained from the
     * {@link ParsedLexeme#ctx}.
     * @return Lexeme from markup.
     */
    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            ImmutableSet.of(),
            Agreement.NONE
        );
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
            this.argumentName().toLowerCase(Locale.getDefault())
        );
    }

    /**
     * Obtains the argument name used to fill out this placeholder.
     * @return Argument name obtained from an ANTLR parse tree.
     */
    private String argumentName() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
