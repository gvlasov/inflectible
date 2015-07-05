package org.tendiwa.lexeme;

/**
 * @author Georgy Vlasov (wlasowegor@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class WrongLanguageException extends IllegalArgumentException {
    WrongLanguageException(String message) {
        super(message);
    }
}
