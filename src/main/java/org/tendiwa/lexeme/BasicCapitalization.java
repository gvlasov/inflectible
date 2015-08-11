package org.tendiwa.lexeme;

/**
 * A word that knows if its first letter is uppercase or lowercase.
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

    private String capitalized() {
        if (this.isFirstLetterCapital()) {
            return this.capitalizableId;
        } else {
            return this.firstLetterToUpperCase();
        }
    }

    private String firstLetterToUpperCase() {
        return String.valueOf(
            Character.toUpperCase(
                this.capitalizableId.charAt(0)
            )
        )
            + this.capitalizableId.substring(1);
    }

    @Override
    public String changeCase(boolean capitalized) {
        return capitalized ? this.capitalized() : this.uncapitalized();
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
