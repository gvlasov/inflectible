package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;

/**
 * A piece of textual markup for a single lexeme.
 * <p/>
 * Consists of:
 * <ol>
 *     <li>Conception id</li>
 *     <li>Names of persistent grammemes</li>
 *     <li>Word forms</li>
 * </ol>
 * <h4>Example of markup:</h4>
 * <pre>
 * conception_id [Persistent,Grammemes] {
 *     wordform_a
 *     wordform_b [Grammeme1,Grammeme2]
 * }
 * </pre>
 * <p/>
 * Unlike {@link Lexeme}, MarkedUpLexeme is just a bunch of Strings that were
 * only syntactically validated by ANTLR, but have not yet been grammatically
 * validated by this library.
 * @see "WordBundleParser.g4, rule `word` for how MarkedUpLexeme is parsed."
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface LexemeMarkup {
    /**
     * @see Localizable#getLocalizationId()
     * @return Identifier of a conception that is represented by this lexeme.
     */
    String conceptionId();

    /**
     * @return Names of grammemes that every word form in this lexeme will have.
     */
    ImmutableList<String> persistentGrammemes();

    /**
     * @return Possible forms this lexeme can assume in various grammatical
     * meanings.
     */
    ImmutableList<WordFormMarkup> wordForms();
}
