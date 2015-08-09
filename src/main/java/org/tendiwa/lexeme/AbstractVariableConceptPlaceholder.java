package org.tendiwa.lexeme;

/**
 * A placeholder with no predefined lexeme.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
abstract class AbstractVariableConceptPlaceholder implements Placeholder {
    protected AbstractVariableConceptPlaceholder() {

    }

    @Override
    public final String fillUp(ActualArguments arguments) {
        return arguments.argumentValue(this.argumentName())
            .formForPlaceholder(this);
    }

    @Override
    public final boolean capitalizes() {
        return new BasicCapitalization(this.identifier())
            .isFirstLetterCapital();
    }

    protected final String argumentName() {
        return new BasicCapitalization(this.identifier()).uncapitalized();
    }

    /**
     * @return The name of a argument that fills up this placeholder, probably
     * capitalized.
     */
    protected abstract String identifier();
}
