package org.tendiwa.lexeme;

/**
 * Body of a {@link MarkedUpText}. Body consists of plain text with
 * placeholders.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface MarkedUpTextBody {
    /**
     * Walks itself with {@code walker} applying
     * {@link BodyWalker#enterPlaceholder(PlaceholderMarkup)} to every placeholder to
     * turn it into actual text.
     * @param walker Body walker
     * @return Text with placeholders filled up.
     */
    String walk(BodyWalker walker);
}
