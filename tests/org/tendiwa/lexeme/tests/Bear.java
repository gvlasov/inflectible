package org.tendiwa.lexeme.tests;

import org.tendiwa.lexeme.Localizable;

public class Bear implements Localizable {
String id = "bear";
@Override
public String getLocalizationId() {
	return id;
}
}
