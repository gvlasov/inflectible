package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

import java.util.Optional;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicPlaceholder implements Placeholder {
    private final String name;
    private final ImmutableSet<Grammeme> explicitMeaning;
    private final Optional<String> agreementName;

    BasicPlaceholder(
        String name,
        ImmutableSet<Grammeme> explicitMeaning,
        Optional<String> agreementName
    ) {
        this.name = name;
        this.explicitMeaning = explicitMeaning;
        this.agreementName = agreementName;
    }

    private boolean capitalizes() {
        return new BasicCapitalization(this.name).isFirstLetterCapital();
    }

    @Override
    public String fillUp(ActualArguments arguments) {
        ImmutableSet<Grammeme> grammaticalMeaning;
        if (this.agreementName.isPresent()) {
            grammaticalMeaning = this.agreementGrammaticalMeaning(
                arguments.argumentValue(this.agreementName.get())
            );
        } else {
            grammaticalMeaning = this.explicitMeaning;
        }
        return new BasicCapitalization(
            arguments.argumentValue(this.name)
            .wordForm(grammaticalMeaning)
        ).changeCase(this.capitalizes());
    }

    private ImmutableSet<Grammeme> agreementGrammaticalMeaning(Lexeme lexeme) {
        return ImmutableSet.<Grammeme>builder()
            .addAll(BasicPlaceholder.this.explicitMeaning)
            .addAll(lexeme.persistentGrammemes())
            .build();
    }
}
