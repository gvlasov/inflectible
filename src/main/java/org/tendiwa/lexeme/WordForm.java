package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface WordForm {
    String spelling();

    int similarity(ImmutableSet<Grammeme> grammemes);
}
