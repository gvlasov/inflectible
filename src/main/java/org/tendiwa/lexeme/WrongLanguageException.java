package org.tendiwa.lexeme;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class WrongLanguageException extends IllegalArgumentException {
    WrongLanguageException(String message) {
        super(message);
    }
}
