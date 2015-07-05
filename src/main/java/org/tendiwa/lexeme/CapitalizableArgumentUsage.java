package org.tendiwa.lexeme;

/**
 * Argument used in a placeholder with first letter probably capitalized..
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class CapitalizableArgumentUsage implements ArgumentUsage {
    private final String capitalizableId;

    /**
     * @param capitalizableId Name of an argument, with first letter probably
     * capitalized.
     */
    CapitalizableArgumentUsage(String capitalizableId) {
        this.capitalizableId = capitalizableId;
    }

    @Override
    public String declaredName() {
        if (Character.isUpperCase(this.capitalizableId.charAt(0))) {
            return firstLetterToLowerCase();
        } else {
            return this.capitalizableId;
        }
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
