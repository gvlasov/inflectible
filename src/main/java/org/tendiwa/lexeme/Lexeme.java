package org.tendiwa.lexeme;

import java.util.List;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Lexeme {
    // TODO: Get rid of this interface, a String will do.
    List<Grammeme> getGrammemes();
}