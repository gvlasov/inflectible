package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
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

    BasicPlaceholder(String name) {
        this(name, ImmutableSet.of(), Optional.empty());
    }

    private boolean capitalizes() {
        return new BasicCapitalization(this.name).isFirstLetterCapital();
    }

    @Override
    public String fillUp(Map<String, Lexeme> arguments) {
        return
            new BasicCapitalization(
                arguments
                    .get(this.name)
                    .wordForm(this.grammaticalMeaning(arguments))
            )
                .changeCase(this.capitalizes());
    }

    private ImmutableSet<Grammeme> grammaticalMeaning(
        Map<String, Lexeme> arguments
    ) {
        ImmutableSet<Grammeme> grammaticalMeaning;
        if (this.agreementName.isPresent()) {
            grammaticalMeaning = this.agreementGrammaticalMeaning(
                arguments.get(this.agreementName.get())
            );
        } else {
            grammaticalMeaning = this.explicitMeaning;
        }
        return grammaticalMeaning;
    }

    private ImmutableSet<Grammeme> agreementGrammaticalMeaning(Lexeme lexeme) {
        return ImmutableSet.<Grammeme>builder()
            .addAll(this.explicitMeaning)
            .addAll(lexeme.persistentGrammemes())
            .build();
    }
}
