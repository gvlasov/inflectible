package org.tendiwa.lexeme;

/**
 * Collection of lexemes belonging to the same denotation.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Word {
    /**
     * The default form of that could go to a dictionary. E.g. infinitive for
     * a verb.
     * @return
     */
    String getBaseForm();

    String findWordForm(GrammaticalMeaning meaning);

    GrammaticalMeaning permanentModifiers();
}
