package org.tendiwa.lexeme;

import org.tendiwa.rocollections.ReadOnlyList;

/**
 *
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface WordBundle {
    ReadOnlyList<LexemeMarkup> words();
}
