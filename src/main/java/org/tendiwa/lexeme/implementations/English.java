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
		super("English", "en_US", English.Grammemes.class);
	}

	@Override
	public String missingWord() {
		return "[parameter_missing]";
	}

	public enum Grammemes implements Grammeme {
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
