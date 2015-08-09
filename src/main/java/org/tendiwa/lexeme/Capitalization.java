package org.tendiwa.lexeme;

/**
 * Some string that may start from a capital letter or not.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface Capitalization {
    /**
     * @return String with its first letter in lower case.
     */
    String uncapitalized();

    /**
     * @return True iff it starts from a capital letter.
     */
    boolean isFirstLetterCapital();
}
