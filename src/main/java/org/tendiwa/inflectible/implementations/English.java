package org.tendiwa.inflectible.implementations;

import org.tendiwa.inflectible.AbstractLanguage;
import org.tendiwa.inflectible.Grammeme;

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
