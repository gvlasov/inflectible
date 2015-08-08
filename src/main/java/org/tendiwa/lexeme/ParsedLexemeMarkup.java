package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link LexemeMarkup} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedLexemeMarkup implements LexemeMarkup {

    private final WordBundleParser.WordContext ctx;

    public ParsedLexemeMarkup(WordBundleParser.WordContext ctx) {
        this.ctx = ctx;
    }

    // TODO: To be refactored in #47
    @Override
    public String conceptionId() {
        return this.ctx.conception().CONCEPTION_ID().getText();
    }

    @Override
    public ImmutableList<String> persistentGrammemes() {
        return this.ctx
            .persistent_grammemes()
            .grammemes()
            .GRAMMEME()
            .stream()
            .map(TerminalNode::getText)
            .collect(Collectors.toImmutableList());
    }

    @Override
    public ImmutableList<WordFormMarkup> wordForms() {
        return this.ctx
            .word_forms()
            .entry()
            .stream()
            .map(ParsedWordFormMarkup::new)
            .collect(Collectors.toImmutableList());
    }
}
