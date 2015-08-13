package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;

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
        this.addPlaceholder(arguments -> text);
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
        public String fillUp(ImmutableList<Lexeme> lexemes) {
            ActualArguments arguments = new ActualArguments(
                BasicTextTemplate.this.argumentNames::indexOf,
                lexemes
            );
            return this.parts.stream()
                .map(part->part.fillUp(arguments))
                .collect(Collectors.joining());
        }
    }
}
