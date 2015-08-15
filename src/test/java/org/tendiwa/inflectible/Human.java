package org.tendiwa.inflectible;

public class Human implements Localizable {
String id = "human";
@Override
public String getLocalizationId() {
	return id;
}
}
