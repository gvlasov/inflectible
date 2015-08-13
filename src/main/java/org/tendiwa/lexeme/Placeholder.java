package org.tendiwa.lexeme;

import java.util.Map;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface Placeholder {
    String fillUp(Map<String, Lexeme> arguments);
}
