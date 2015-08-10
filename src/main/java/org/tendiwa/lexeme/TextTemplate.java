package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;

/**
 * Text with unfilled placeholders.
 * A single marked up text. It consists of three parts:
 * <ol>
 *     <li>Localization argumentName;</li>
 *     <li>List of argument names;</li>
 *     <li>Template body.</li>
 * </ol>
 * <pre>
 * localization_id (param1, param2, param3) {
 *     Text about [param1] and [param2][Plur] mentioning [param3][Gerund].
 * }
 * </pre>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public interface TextTemplate {

    /**
     * @param lexemes Lexemes to be inserted in place of placeholders
     * @return Text with placeholders filled up.
     */
    String fillUp(ImmutableList<Lexeme> lexemes);
}
