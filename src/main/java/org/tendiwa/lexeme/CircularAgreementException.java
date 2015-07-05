package org.tendiwa.lexeme;

/**
 * Occurs when member A is in an agreement relationship with member B, and
 * member B is with A, e.g.:
 * <pre>
 *     some.action(actor, seer) {
 *         [Actor][;seer]. And then [seer][;actor].
 *     }
 * </pre>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class CircularAgreementException extends RuntimeException {
    CircularAgreementException(String message) {
        super(message);
    }
}
