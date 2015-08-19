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
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * {@link TemplateBodyPiece} constructed from an ANTLR parse tree.
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

    /**
     * Creates a placeholder with aspects obtained from the
     * {@link ParsedLexeme#ctx}.
     * @return Lexeme from markup.
     */
    private Placeholder delegate() {
        return this.withCapitalizaton(
            new PhFromArgument(
                new ArgumentName(
                    this.argumentIdentifier().toLowerCase()
                )
            )
        );
    }


    // To be refactored in #47
    /**
     * Adds capitalization if it was declared in markup.
     * @param placeholder Placeholder to decorate
     * @return Probably capitalized placeholder
     */
    private Placeholder withCapitalizaton(final Placeholder placeholder) {
        final Placeholder modified;
        if (Character.isUpperCase(this.argumentIdentifier().charAt(0))) {
            modified = new PhWithCapitalization(placeholder);
        } else {
            modified = placeholder;
        }
        return modified;
    }

    /**
     * Obtains the argument name (probably capitalized) used to fill out this
     * placeholder.
     * @return Argument name obtained from an ANTLR parse tree.
     */
    private String argumentIdentifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
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
}
