package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

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
        return this.ctx.WORD_FORM().getText();
    }

    @Override
    public ImmutableList<String> grammemes() {
        return this.ctx.grammemes().GRAMMEME()
            .stream()
            .map(TerminalNode::getText)
            .collect(Collectors.toImmutableList());
    }
}
