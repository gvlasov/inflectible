package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * Grammatical meaning of a placeholder in some marked up text.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class PlaceholderGrammaticalMeaning implements GrammaticalMeaning {
    private final Language language;
    private final Placeholder placeholder;
    private final MarkupArguments arguments;

    /**
     * @param placeholder Placeholder in the marked up text.
     * @param language Language of the marked up text.
     * @param arguments Arguments in the marked up text.
     */
    public PlaceholderGrammaticalMeaning(
        Placeholder placeholder,
        Language language,
        MarkupArguments arguments
    ) {
        this.language = language;
        this.placeholder = placeholder;
        this.arguments = arguments;
    }

    @Override
    public ImmutableSet<Grammeme> grammemes() {
        final ImmutableSet.Builder<Grammeme> builder = ImmutableSet.builder();
        if (this.placeholder.agreementId().isPresent()) {
            builder.addAll(this.agreementGrammemes());
        }
        for (String grammemeName : this.placeholder.explicitGrammemes()) {
            builder.add(this.language.stringToModifier(grammemeName));
        }
        return builder.build();
    }

    private ImmutableSet<Grammeme> agreementGrammemes() {
        return this.arguments
            .getArgument(this.placeholder.agreementId().get())
            .permanentModifiers()
            .grammemes();
    }
}
