package org.tendiwa.lexeme;

/**
 * Body of a {@link MarkedUpText}. Body consists of plain text with
 * placeholders.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface MarkedUpTextBody {
    void walk(BodyWalker walker);
}
