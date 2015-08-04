package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.tendiwa.rocollections.WrappingReadOnlySet;

/**
 * Grammatical meaning of a placeholder in some marked up text.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class PlaceholderGrammaticalMeaning
    extends WrappingReadOnlySet<Grammeme>
    implements GrammaticalMeaning {

    /**
     * @param placeholder Placeholder in the marked up text
     * @param grammar Grammar of the target language
     * @param arguments Arguments in the marked up text
     */
    public PlaceholderGrammaticalMeaning(
        Placeholder placeholder,
        Grammar grammar,
        ActualArguments arguments
    ) {
        super(
             PlaceholderGrammaticalMeaning.grammemes(
                placeholder,
                grammar,
                arguments
            )
        );
    }

    private static ImmutableSet<Grammeme> grammemes(
        Placeholder placeholder,
        Grammar grammar,
        ActualArguments arguments
    ) {
        final ImmutableSet.Builder<Grammeme> builder = ImmutableSet.builder();
        if (placeholder.agreementId().isPresent()) {
            builder.addAll(
                PlaceholderGrammaticalMeaning.agreementGrammemes(
                    arguments,
                    placeholder
                )
            );
        }
        for (String grammemeName : placeholder.explicitGrammemes()) {
            builder.add(grammar.grammemeByName(grammemeName));
        }
        return builder.build();
    }

    private static ImmutableSet<Grammeme> agreementGrammemes(
        ActualArguments arguments,
        Placeholder placeholder
    ) {
        return arguments
            .argumentValue(placeholder.agreementId().get())
            .persistentGrammemes();
    }
}
