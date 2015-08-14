package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
@FunctionalInterface
public interface Agreement {
    Agreement NONE = arguments -> ImmutableSet.of();

    ImmutableSet<Grammeme> grammemes(Map<String, Lexeme> arguments);
}
