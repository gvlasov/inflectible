package org.tendiwa.lexeme.tests;

import org.tendiwa.lexeme.Localizable;

public class Human implements Localizable {
String id = "human";
@Override
public String getLocalizationId() {
	return id;
}
}
