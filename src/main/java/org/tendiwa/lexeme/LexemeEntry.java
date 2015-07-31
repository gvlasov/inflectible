package org.tendiwa.lexeme;

/**
 * An entry in a lexeme.
 * <p/>
 * Example:
 * <pre>
 * "bear" {
 *     bear  [Sing]
 *     bears [Plur]
 * }
 * </pre>
 * Two lines inside lexeme "bear" are referred to as lexeme entries.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface LexemeEntry {
    /**
     * @return Word form as it appears in text.
     */
    String wordForm();

    /**
     * @return Grammatical meaning of {@link LexemeEntry#wordForm()}
     */
    GrammaticalMeaning grammaticalMeaning();
}
