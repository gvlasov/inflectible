package org.tendiwa.lexeme;

import java.util.List;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface TextBundle {
    /**
     * @return Texts in this corpus.
     */
    List<TextTemplate> texts();
}
