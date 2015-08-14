package org.tendiwa.lexeme;

/**
 * Knows words used to refer to conceptions.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface NativeSpeaker {
    String text(String textId, Localizable... arguments);
}
