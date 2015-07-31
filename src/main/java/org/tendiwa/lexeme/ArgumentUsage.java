package org.tendiwa.lexeme;

/**
 * Declaration that occurs in the header line of each marked up text inside
 * parenthesis, denoting an argument.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface ArgumentUsage {
    /**
     * Name of the argument, as declared in the header line.
     * @return A string that is a valid Java identifier starting with a
     * lowercase ASCII letter.
     */
    String declaredName();
}
