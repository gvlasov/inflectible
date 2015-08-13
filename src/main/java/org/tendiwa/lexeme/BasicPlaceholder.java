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
    private boolean capitalizes;
    private final ImmutableSet<Grammeme> explicitMeaning;
    private final Optional<String> agreementName;

    BasicPlaceholder(
        String name,
        boolean capitalizes,
        ImmutableSet<Grammeme> explicitMeaning,
        Optional<String> agreementName
    ) {
        this.name = name;
        this.capitalizes = capitalizes;
        this.explicitMeaning = explicitMeaning;
        this.agreementName = agreementName;
    }


    BasicPlaceholder(String name) {
        this(name, false, ImmutableSet.of(), Optional.empty());
    }

    private boolean capitalizes() {
        return this.capitalizes;
    }

    @Override
    public String fillUp(Map<String, Lexeme> arguments) {
        return
            new BasicCapitalization(
                arguments
                    .get(this.validatedName())
                    .wordForm(this.grammaticalMeaning(arguments))
            )
                .changeCase(this.capitalizes());
    }

    private String validatedName() {
        if (!this.name.toLowerCase().equals(this.name)) {
            throw new IllegalArgumentException(
                String.format(
                    "Argument name should be lowercase; now it is %s",
                    this.name
                )
            );
        }
        return this.name;
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
