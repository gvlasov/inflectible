package org.tendiwa.lexeme.implementations;

import org.tendiwa.lexeme.AbstractLanguage;
import org.tendiwa.lexeme.Grammeme;

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
	public String missingWord() {
		return "[parameter_missing]";
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
