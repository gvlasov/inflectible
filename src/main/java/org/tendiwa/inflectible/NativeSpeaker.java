package org.tendiwa.inflectible;

/**
 * Knows how to speak a language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface NativeSpeaker {
    /**
     * Fills out a text.
     * @param textId Id of a text template to fill out
     * @param arguments Conceptions that have words in vocabulary for them.
     *  Will be used to fill out {@link Placeholder}s in {@link TextTemplate}.
     * @return Filled out text.
     */
    String text(String textId, Localizable... arguments);
}
