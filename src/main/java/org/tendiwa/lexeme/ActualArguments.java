package org.tendiwa.lexeme;

import org.tendiwa.rocollections.ReadOnlyList;

/**
 * Actual values of arguments passed in a text to fill up placeholders.
 * @see {@link DeclaredArguments} For a list of argument names without actual
 *  values.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ActualArguments {
    private final ReadOnlyList<Lexeme> values;
    private final DeclaredArguments declaredArguments;

    ActualArguments(
        DeclaredArguments declaredArguments,
        ReadOnlyList<Lexeme> values
    ) {
        this.declaredArguments = declaredArguments;
        this.values = values;
    }

    /**
     * @param argumentName Declared name of argument.
     * @return Value of the argument with particular name.
     */
    public final Lexeme argumentValue(String argumentName) {
        final int index = this.declaredArguments.index(argumentName);
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
