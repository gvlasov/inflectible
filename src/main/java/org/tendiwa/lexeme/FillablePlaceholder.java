package org.tendiwa.lexeme;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface FillablePlaceholder extends Placeholder {
    String fillUp(ActualArguments arguments);
}
