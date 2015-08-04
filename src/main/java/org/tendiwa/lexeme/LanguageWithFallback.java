package org.tendiwa.lexeme;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public abstract class LanguageWithFallback extends AbstractLanguage {
    private final Language fallbackLanguage;
    /**
     * @param localizedName Name of the language in that language, for example "Русский" for Russian.
     * @param localeName
     */
    protected LanguageWithFallback(
        String localizedName,
        String localeName,
        Class<? extends Grammeme> grammemesClass,
        Language fallbackLanguage
    ) {
        super(localizedName, localeName, grammemesClass);
        this.fallbackLanguage = fallbackLanguage;
    }
}
