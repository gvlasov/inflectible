package org.tendiwa.lexeme;

/**
 * Stores marked up texts. Same as {@link NativeSpeaker}, but
 * for text rather than for words.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Textuary {
    MarkedUpText getText(String name);
}
