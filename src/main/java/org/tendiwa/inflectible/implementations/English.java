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
    /**
     * Ctor.
     */
	public English() {
		super(English.Grammemes.class);
	}

    /**
     * Grammemes of English grammar.
     */
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
