package org.tendiwa.lexeme;

/**
 * Knows words used to refer to conceptions.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface NativeSpeaker {
    /**
     * @param conception A thing that can be named with a lexeme.
     * @return Word for a conception.
     */
    Lexeme wordFor(Localizable conception);
}
