package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class TextTemplateBuilder {

    private ImmutableList<String> argumentNames;

    private final List<Placeholder> parts;

    private boolean used;

    TextTemplateBuilder(ImmutableList<String> argumentNames) {
        this.argumentNames = argumentNames;
        this.parts = new ArrayList<>(
            TextTemplateBuilder.expectedPartsNumber(argumentNames)
        );
        this.used = false;
    }

    public TextTemplateBuilder addPlaceholder(Placeholder placeholder) {
        this.parts.add(placeholder);
        return this;
    }

    public TextTemplateBuilder addText(String text) {
        this.addPlaceholder((arguments, vocabulary) -> text);
        return this;
    }

    public TextTemplate build() {
        if (this.used) {
            throw new IllegalStateException(
                "This builder has already been used"
            );
        }
        this.used = true;
        return new BasicTextTemplate(this.argumentNames, this.parts);
    }

    private static int expectedPartsNumber(
        ImmutableList<String> argumentNames
    ) {
        return argumentNames.size()*2+1;
    }

    private class BasicTextTemplate implements TextTemplate {
        private final ImmutableList<String> argumentNames;
        private final List<Placeholder> parts;

        BasicTextTemplate(
            ImmutableList<String> argumentNames,
            List<Placeholder> parts
        ) {
            this.argumentNames = argumentNames;
            this.parts = parts;
        }

        @Override
        public String fillUp(
            ImmutableList<Lexeme> lexemes,
            ImmutableMap<String, Lexeme> vocabulary
        ) {
            final ImmutableMap<String, Lexeme> actualArguments =
                this.actualArguments(lexemes);
            return this.parts.stream()
                .map(part -> part.fillUp(actualArguments, vocabulary))
                .collect(Collectors.joining());
        }

        private ImmutableMap<String, Lexeme> actualArguments(
            List<Lexeme> lexemes
        ) {
            if (lexemes.size() != this.argumentNames.size()) {
                throw new IllegalArgumentException(
                    String.format(
                        "Wrong number of arguments. Expected: %s, actual: %s",
                        this.argumentNames.size(),
                        lexemes.size()
                    )
                );
            }
            final ImmutableMap.Builder<String, Lexeme> builder =
                ImmutableMap.builder();
            for (int i=0; i<lexemes.size(); i++) {
                builder.put(this.argumentNames.get(i), lexemes.get(i));
            }
            return builder.build();
        }
    }
}
