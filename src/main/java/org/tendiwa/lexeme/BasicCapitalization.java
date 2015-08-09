package org.tendiwa.lexeme;

/**
 * Argument used in a placeholder with first letter probably capitalized.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicCapitalization implements Capitalization {
    private final String capitalizableId;

    /**
     * @param capitalizableId Name of an argument, with first letter probably
     * capital.
     */
    BasicCapitalization(String capitalizableId) {
        this.capitalizableId = capitalizableId;
    }

    @Override
    public String uncapitalized() {
        if (this.isFirstLetterCapital()) {
            return this.firstLetterToLowerCase();
        } else {
            return this.capitalizableId;
        }
    }

    @Override
    public boolean isFirstLetterCapital() {
        return Character.isUpperCase(this.capitalizableId.charAt(0));
    }

    private String firstLetterToLowerCase() {
        return String.valueOf(
            Character.toLowerCase(
                this.capitalizableId.charAt(0)
            )
        )
            + this.capitalizableId.substring(1);
    }
}
