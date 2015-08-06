package org.tendiwa.lexeme;

import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tendiwa.rocollections.ReadOnlyList;

/**
 * WordFormMarkup parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedWordFormMarkup implements WordFormMarkup {
    private final WordBundleParser.EntryContext ctx;

    ParsedWordFormMarkup(WordBundleParser.EntryContext ctx) {
        this.ctx = ctx;
    }
    @Override
    public String spelling() {
        return null;
    }

    @Override
    public ReadOnlyList<String> grammemes() {
        return null;
    }
}
