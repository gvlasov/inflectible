package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Placeholder {
    ImmutableSet<Grammeme> grammaticalMeaning();
}
