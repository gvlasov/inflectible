package org.tendiwa.lexeme;

/**
 * Text with unfilled placeholders.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public interface MarkedUpText {

    /**
     * Id of this text. Corresponds to entry_id rule in TextBundleParser.g4.
     * @return Id of this text.
     */
    String id();
    /**
     * Fill up placehodlers with lexemes.
     * @param denotations Localizables whose lexemes to use to fill up the
     * marked up text.
     * @return Plain localized text.
     */
    String fillUp(Localizable... denotations);
}
