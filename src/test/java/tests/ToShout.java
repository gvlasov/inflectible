package tests;

import org.tendiwa.lexeme.Localizable;

public class ToShout implements Localizable {
String id = "to shout";

@Override
public String getLocalizationId() {
	return id;
}
}
