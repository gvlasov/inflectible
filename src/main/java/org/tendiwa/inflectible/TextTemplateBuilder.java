package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A builder used to create {@link TextTemplate}s.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class TextTemplateBuilder {
    /**
     * Names of the arguments of a template.
     */
    private final ImmutableList<String> arguments;

    /**
     * Parts of a template. Those can be either placeholders or plain text
     * parts (which may be seen as constant-value placeholders, hence the type).
     */
    private final List<Placeholder> parts;

    /**
     * If this builder has already been used to produce a template.
     */
    private boolean used;

    /**
     * Ctor.
     * @param names Names of the arguments of a template
     */
    TextTemplateBuilder(ImmutableList<String> names) {
        this.arguments = names;
        this.parts = new ArrayList<>(
            TextTemplateBuilder.expectedPartsNumber(names)
        );
        this.used = false;
    }

    /**
     * Adds a placeholder to the body of the constructed template.
     * @param placeholder A placeholder
     * @return This builder
     */
    public TextTemplateBuilder addPlaceholder(Placeholder placeholder) {
        this.parts.add(placeholder);
        return this;
    }

    /**
     * Adds plain text to the body of the constucted template.
     * @param text Plain text
     * @return This builder
     */
    public TextTemplateBuilder addText(String text) {
        this.addPlaceholder((arguments, vocabulary) -> text);
        return this;
    }

    /**
     * Builds a template.
     * @return A new {@link TextTemplate}
     */
    public TextTemplate build() {
        if (this.used) {
            throw new IllegalStateException(
                "This builder has already been used"
            );
        }
        this.used = true;
        return new BasicTextTemplate(this.arguments, this.parts);
    }

    /**
     * A good approximated size for an array list that will hold the parts of
     * this text.
     * @param arguments arguments to this text.
     * @return Good size for an array list.
     */
    private static int expectedPartsNumber(
        ImmutableList<String> arguments
    ) {
        return arguments.size()*2+1;
    }

    /**
     * {@link TextTemplate} defined by its arguments' names and a
     * heterogeneous list of its parts (placeholders and plain text chunks).
     */
    private class BasicTextTemplate implements TextTemplate {
        /**
         * Argument names.
         */
        private final ImmutableList<String> argumentNames;
        /**
         * Heterogeneous list of template's parts (placeholders and plain text
         * chunks)
         */
        private final List<Placeholder> parts;

        /**
         * Ctor.
         * @param names Argument names
         * @param parts Heterogeneous list of template's parts (placeholders
         *  and plain text chunks)
         */
        BasicTextTemplate(
            ImmutableList<String> names,
            List<Placeholder> parts
        ) {
            this.argumentNames = names;
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

        /**
         * Creates a map of actual arguments.
         * @param lexemes Lexemes passed to this template to fill it out.
         * @return Map from argument names to lexemes passed for those
         *  arguments.
         */
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
