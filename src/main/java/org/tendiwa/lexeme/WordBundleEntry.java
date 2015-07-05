package org.tendiwa.lexeme;

/**
 * A link between a conception and a lexeme used to refer to it.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface WordBundleEntry {
    /**
     * @return Conception id under which to save {@link #lexeme()}.
     */
    String conceptionId();

    /**
     * @return A collection of word forms with their grammemes.
     */
    Lexeme lexeme();
}
