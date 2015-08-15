package org.tendiwa.inflectible;

import java.util.function.Function;

/**
 * Changes capitalization stype of a string.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
interface Capitalization extends Function<String, String> {
    Capitalization CAPITALZES =
        word ->
            String.format(
                "%c%s",
                Character.toUpperCase(word.charAt(0)),
                word.substring(1)
            );

    Capitalization IDENTITY = word -> word;

    @Override
    String apply(String word);
}
