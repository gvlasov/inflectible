package org.tendiwa.inflectible;

/**
 * Localizable object created out of an int. Its localizationId will be this
 * very int stringified, for example "27" for 27.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class LocalizableNumber implements Localizable {
    private final String localizationId;

    public LocalizableNumber(int number) {
        this.localizationId = Integer.toString(number);
    }

    @Override
    public String getLocalizationId() {
        return localizationId;
    }
}
