package org.tendiwa.inflectible;

/**
 * Grammar of a natural language. Knows grammemes by their name.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Grammar {
    /**
     * Get a grammeme by its name.
     * @param name Name of a grammeme
     * @return Grammeme by its name.
     * @throws IllegalArgumentException If there is no Grammeme with such name.
     */
    Grammeme grammemeByName(String name);
}
