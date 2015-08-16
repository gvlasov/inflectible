package org.tendiwa.inflectible;

/**
 * A natural language. In order to work with templates in a particular
 * language, this interface is all you need to implement.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Language {
    Grammar grammar();
}
