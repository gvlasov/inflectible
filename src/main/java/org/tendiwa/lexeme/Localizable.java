package org.tendiwa.lexeme;

/**
 * Anything that can be named. For each Localizable, there may be a single
 * word in each language, identified by localization id.
 */
public interface Localizable {
	/**
     * Identifier of a word.
	 * @return A string identifier. May contain only English letters.
	 */
	String getLocalizationId();
}
