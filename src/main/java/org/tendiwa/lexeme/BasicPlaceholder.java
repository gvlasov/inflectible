package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.Optional;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicPlaceholder implements FillablePlaceholder {
    private final String name;
    private final ImmutableSet<Grammeme> grammaticalMeaning;
    private final Optional<String> agreementName;

    BasicPlaceholder(
        String name,
        ImmutableSet<Grammeme> grammaticalMeaning,
        Optional<String> agreementName
    ) {
        this.name = name;
        this.grammaticalMeaning = grammaticalMeaning;
        this.agreementName = agreementName;
    }

    @Override
    public ImmutableSet<Grammeme> grammaticalMeaning() {
        return this.grammaticalMeaning;
    }

    private boolean capitalizes() {
        return new BasicCapitalization(this.name).isFirstLetterCapital();
    }

    @Override
    public String fillUp(ActualArguments arguments) {
        Placeholder placeholder;
        if (this.agreementName.isPresent()) {
            placeholder = new AgreeingPlaceholder(
                arguments.argumentValue(this.agreementName.get())
            );
        } else {
            placeholder = this;
        }
        return new BasicCapitalization(
            arguments.argumentValue(this.name)
            .formForPlaceholder(placeholder)
        ).changeCase(this.capitalizes());
    }

    private final class AgreeingPlaceholder implements Placeholder {
        private final Lexeme lexeme;

        public AgreeingPlaceholder(Lexeme lexeme) {
            this.lexeme = lexeme;
        }

        @Override
        public ImmutableSet<Grammeme> grammaticalMeaning() {
            return ImmutableSet.<Grammeme>builder()
                .addAll(BasicPlaceholder.this.grammaticalMeaning())
                .addAll(this.lexeme.persistentGrammemes())
                .build();
        }
    }
}
