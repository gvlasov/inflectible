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

/**
 * {@link Placeholder} defined by its aspects.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicPlaceholder implements Placeholder {
    private final LexemeSource lexemeSource;
    private final Capitalization capitalization;
    private final ImmutableSet<Grammeme> explicitGrammemes;
    private final Agreement agreement;

    BasicPlaceholder(
        LexemeSource lexemeSource,
        Capitalization capitalization,
        ImmutableSet<Grammeme> explicitGrammemes,
        Agreement agreement
    ) {
        this.lexemeSource = lexemeSource;
        this.capitalization = capitalization;
        this.explicitGrammemes = explicitGrammemes;
        this.agreement = agreement;
    }

    public BasicPlaceholder(LexemeSource lexemeSource) {
        this(
            lexemeSource,
            Capitalization.IDENTITY,
            ImmutableSet.of(),
            Agreement.NONE
        );
    }

    public BasicPlaceholder withCapitalization(Capitalization capitalization) {
        if (this.capitalization.equals(capitalization)) {
            return this;
        } else {
            return new BasicPlaceholder(
                this.lexemeSource,
                capitalization,
                this.explicitGrammemes,
                this.agreement
            );
        }
    }

    public BasicPlaceholder withAgreement(Agreement agreement) {
        if (this.agreement.equals(agreement)) {
            return this;
        } else {
            return new BasicPlaceholder(
                this.lexemeSource,
                this.capitalization,
                this.explicitGrammemes,
                agreement
            );
        }
    }

    public BasicPlaceholder withExplicitGrammemes(
        ImmutableSet<Grammeme> grammemes
    ) {
        if (this.explicitGrammemes.equals(grammemes)) {
            return this;
        } else {
            return new BasicPlaceholder(
                this.lexemeSource,
                this.capitalization,
                grammemes,
                this.agreement
            );
        }
    }

    @Override
    public String fillUp(
        ImmutableMap<String, Lexeme> arguments,
        ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.capitalization.apply(
            this.lexemeSource.lexeme(arguments, vocabulary)
            .wordForm(
                ImmutableSet.<Grammeme>builder()
                    .addAll(this.agreement.grammemes(arguments))
                    .addAll(this.explicitGrammemes)
                    .build()
            )
        );
    }
}
