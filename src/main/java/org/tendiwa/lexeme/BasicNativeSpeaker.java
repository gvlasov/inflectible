package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.tenidwa.collections.utils.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicNativeSpeaker implements NativeSpeaker {
    private final ImmutableMap<String, Lexeme> vocabulary;
    private final ImmutableMap<String, TextTemplate> textuary;

    BasicNativeSpeaker(
        ImmutableMap<String, Lexeme> vocabulary,
        ImmutableMap<String, TextTemplate> textuary
    ) {
        this.vocabulary = vocabulary;
        this.textuary = textuary;
    }
    @Override
    public Lexeme wordFor(Localizable conception) {
        return this.vocabulary.get(conception.getLocalizationId());
    }

    @Override
    public String text(String textTemplateId, Localizable... arguments) {
        return this.textuary.get(textTemplateId).fillUp(
            ImmutableList.copyOf(arguments)
                .stream()
                .map(argument -> this.vocabulary.get(argument.getLocalizationId()))
                .collect(Collectors.toImmutableList()),
            this.vocabulary
        );
    }
}
