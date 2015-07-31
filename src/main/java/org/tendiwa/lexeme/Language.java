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
     * Get a grammeme by its name.
     * <p/>
     * This method in introduced to get the base implementation of Language
     * acquainted with concrete implementation's Grammemes enum. So, in most
     * cases, the body of this method will simply be:
     * <pre>
     *     Grammemes.valueOf(name);
     * </pre>
     * Where {@code Grammemes} is an inner enum in your concrete implementation.
     * @param name Name of enum instance. It is perfectly fine to name
     * instances in your localized language (like {@code Grammemes.Муж} in
     * Russian), and thus it is fine to pass a localized string as this
     * parameter.
     * @return A Grammemes enum instance whose
     *  {@code enumInstance.valueOf().equals(name)}
     */
    Grammeme grammemeByName(String name);

    String missingWord();


    String getLocaleName();

    String getLocalizedName();
}
