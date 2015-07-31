package org.tendiwa.lexeme;

import java.net.URL;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Language {
    boolean validateLanguage(URL url);

    /**
     * Implementation translate a String instance to a Modifier instance.
     * This method in introduced to get the base implementation of Language
     * acquainted with concrete implementation's Modifiers enum. So, in most
     * cases, the body of this method will simply be:
     * <pre>
     *     Modifiers.valueOf(modifier);
     * </pre>
     * Where {@code Modifiers} is an inner enum in your concrete implementation.
     * @param modifier Name of enum instance. It is perfectly fine to name
     * instances in your localized language (like {@code Modifiers.Муж}), and
     * thus it is fine to pass a localized string as this parameter.
     * @return A modifier enum instance whose
     * {@code enumInstance.valueOf().equals(modifier)}
     */
    Grammeme stringToModifier(String modifier);

    String missingWord();


    String getLocaleName();

    String getLocalizedName();
}
