package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
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
