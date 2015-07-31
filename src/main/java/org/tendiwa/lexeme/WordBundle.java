package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;

/**
 *
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface WordBundle {
    ImmutableList<WordBundleEntry> words();
}
