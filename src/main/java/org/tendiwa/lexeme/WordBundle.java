package org.tendiwa.lexeme;

import java.util.stream.Stream;

/**
 *
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface WordBundle {
    Stream<LexemeMarkup> words();
}
