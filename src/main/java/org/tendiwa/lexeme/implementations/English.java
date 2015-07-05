package org.tendiwa.lexeme.implementations;

import java.util.List;
import org.tendiwa.lexeme.AbstractLanguage;
import org.tendiwa.lexeme.Grammeme;
import org.tendiwa.lexeme.LexemeTemplate;
import org.tendiwa.lexeme.Localizable;

/**
 * English language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class English extends AbstractLanguage {
	public English() {
		super("English", "en_US");
	}

	@Override
	public Grammeme stringToModifier(String modifier) {
		return English.Modifiers.valueOf(modifier);
	}

	@Override
	public String getMissingWord() {
		return "[parameter_missing]";
	}

	@Override
	public List<Grammeme> processTemplate(LexemeTemplate lexemeTemplate, Localizable localizable) {
		return null;
	}

	public enum Modifiers implements Grammeme {
        /**
         * Gerund.
         */
		Ger,
        /**
         * Singular.
         */
		Sing,
        /**
         * Plural.
         */
		Plur,
        /**
         * Third person.
         */
		III
	}
}
