package org.tendiwa.lexeme.tests;

import junit.framework.TestCase;
import org.tendiwa.lexeme.Language;
import org.tendiwa.lexeme.Localizable;
import org.tendiwa.lexeme.implementations.English;
import org.tendiwa.lexeme.implementations.Russian;

public class TwoLanguagesTest extends TestCase {
public void test() {
	Language english = new English();
	Language russian = new Russian();

	english.loadVocabulary(getClass().getResource("/characters.en_US.words"));
	russian.loadVocabulary(getClass().getResource("/characters.ru_RU.words"));
	english.loadVocabulary(getClass().getResource("/actions.en_US.words"));
	russian.loadVocabulary(getClass().getResource("/actions.ru_RU.words"));
	english.loadCorpus(getClass().getResourceAsStream("/messages.en_US.texts"));
	russian.loadCorpus(getClass().getResourceAsStream("/messages.ru_RU.texts"));

	String englishText = english.getLocalizedText(
		"log.get_damage",
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "bear";
			}
		},
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "to bite";
			}
		},
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "human";
			}
		}
	);
	String russianText = russian.getLocalizedText(
		"log.get_damage",
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "bear";
			}
		},
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "to bite";
			}
		},
		new Localizable() {
			@Override
			public String getLocalizationId() {
				return "human";
			}
		}
	);
	assertEquals(englishText, "Bear bites human");
	assertEquals(russianText, "Медведь кусает человека");
}
}
