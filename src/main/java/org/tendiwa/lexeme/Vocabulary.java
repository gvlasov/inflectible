package org.tendiwa.lexeme;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Vocabulary {
    /**
     * Get word by its id.
     * @param id
     * @return
     */
    Word getWord(String id);
}
