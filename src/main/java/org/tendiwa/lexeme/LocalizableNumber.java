package org.tendiwa.lexeme;

public class LocalizableNumber implements Localizable {
private final String localizationId;
public LocalizableNumber(int number) {
	localizationId = Integer.toString(number);
}

@Override
public String getLocalizationId() {
	return localizationId;
}
}
