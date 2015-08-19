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

/**
 * A {@link Placeholder} defined by its aspects.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicPlaceholder implements Placeholder {
    /**
     * Lexeme source aspect.
     */
    private final transient LexemeSource source;

    /**
     * Capitalization aspect.
     */
    private final transient Capitalization capitalization;
    /**
     * Explicit grammemes aspect.
     */
    private final transient ImmutableSet<Grammeme> explicit;

    /**
     * Agreement aspect.
     */
    private final transient Agreement agreement;

    /**
     * Ctor.
     * @param lexeme Lexeme source aspect
     * @param capital Capitalization aspect
     * @param grammemes Explicit grammemes aspect
     * @param agree Agreement aspect
     * @checkstyle ParameterNumberCheck (3 lines)
     */
    BasicPlaceholder(
        final LexemeSource lexeme,
        final Capitalization capital,
        final ImmutableSet<Grammeme> grammemes,
        final Agreement agree
    ) {
        this.source = lexeme;
        this.capitalization = capital;
        this.explicit = grammemes;
        this.agreement = agree;
    }

    /**
     * Ctor.
     * @param lexemes Lexeme source aspect
     */
    public BasicPlaceholder(final LexemeSource lexemes) {
        this(
            lexemes,
            Capitalization.IDENTITY,
            ImmutableSet.of(),
            Agreement.NONE
        );
    }

    /**
     * Creates a placeholder that is the same, but with different
     * capitalization.
     * @param capital Capitalization aspect
     * @return Placeholder with different capitalization
     */
    public BasicPlaceholder withCapitalization(final Capitalization capital) {
        final BasicPlaceholder placeholder;
        if (this.capitalization.equals(capital)) {
            placeholder = this;
        } else {
            placeholder = new BasicPlaceholder(
                this.source,
                capital,
                this.explicit,
                this.agreement
            );
        }
        return placeholder;
    }

    /**
     * Creates a placeholder that is the same, but with different agreement.
     * @param agree Agreemen aspect
     * @return Placeholder with different agreement
     */
    public BasicPlaceholder withAgreement(final Agreement agree) {
        final BasicPlaceholder placeholder;
        if (this.agreement.equals(agree)) {
            placeholder = this;
        } else {
            placeholder = new BasicPlaceholder(
                this.source,
                this.capitalization,
                this.explicit,
                agree
            );
        }
        return placeholder;
    }

    /**
     * Creates a placeholder that is the same, but with different explicit
     * grammemes.
     * @param grammemes Expilcit grammemes aspect
     * @return Placeholder with different explicit grammemes
     */
    public BasicPlaceholder withExplicitGrammemes(
        final ImmutableSet<Grammeme> grammemes
    ) {
        final BasicPlaceholder placeholder;
        if (this.explicit.equals(grammemes)) {
            placeholder = this;
        } else {
            placeholder = new BasicPlaceholder(
                this.source,
                this.capitalization,
                grammemes,
                this.agreement
            );
        }
        return placeholder;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return this.capitalization.apply(
            this.source.lexeme(arguments, vocabulary)
                .wordForm(
                    ImmutableSet.<Grammeme>builder()
                        .addAll(this.agreement.grammemes(arguments))
                        .addAll(this.explicit)
                        .build()
                )
                .string()
        );
    }
}
