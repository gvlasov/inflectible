package org.tendiwa.lexeme;

import java.net.URL;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Language {
    boolean validateLanguage(URL url);

    String missingWord();

    String getLocaleName();

    String getLocalizedName();

    Grammar grammar();
}
