package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Actual values of arguments passed in a text to fill up placeholders.
 * @see {@link DeclaredArguments} For a list of argument names without actual
 *  values.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ActualArguments {
    private final List<Lexeme> values;
    private final List<String> argumentNames;

    ActualArguments(
        ImmutableList<String> argumentNames,
        ImmutableList<Lexeme> values
    ) {
        this.argumentNames = argumentNames;
        this.values = values;
    }

    /**
     * @param argumentName Declared name of argument.
     * @return Value of the argument with particular name.
     */
    public final Lexeme argumentValue(String argumentName) {
        final int index = this.argumentNames.indexOf(argumentName);
        if (this.values.size() <= index) {
            throw new IndexOutOfBoundsException(
                "Declared argument " + argumentName + " is at index " + index +
                    ", but only " + this.values.size() +
                    "values have been passed as actual arguments"
            );
        }
        final Lexeme lexeme = this.values.get(index);
        if (lexeme == null) {
            throw new IllegalArgumentException(
                "No argument with name \"" + argumentName + "\" "
            );
        }
        return lexeme;
    }
}
