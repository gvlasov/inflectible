package org.tendiwa.lexeme;

/**
 * A placeholder with no predefined lexeme.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
abstract class AbstractVariableConceptPlaceholder implements FillablePlaceholder {
    protected AbstractVariableConceptPlaceholder() {

    }

    @Override
    public final boolean capitalizes() {
        return new BasicCapitalization(this.identifier())
            .isFirstLetterCapital();
    }

    /**
     * @return Name of the argument that provides a lexeme for this placeholder.
     * @see AbstractVariableConceptPlaceholder#identifier() For that same
     *  name, but possibly capitalized.
     */
    protected final String argumentName() {
        return new BasicCapitalization(this.identifier()).uncapitalized();
    }

    /**
     * @return The name of a argument that fills up this placeholder, probably
     * capitalized.
     */
    protected abstract String identifier();
}
