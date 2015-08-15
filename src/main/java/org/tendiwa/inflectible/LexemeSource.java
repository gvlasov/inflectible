package org.tendiwa.inflectible;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface LexemeSource {
    Lexeme lexeme(
        Map<String, Lexeme> arguments,
        Map<String, Lexeme> vocabulary
    );
}
