package org.tendiwa.lexeme;

import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * {@link LexemeMarkup} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedLexemeMarkup implements LexemeMarkup {

    private final WordBundleParser.WordContext ctx;

    public ParsedLexemeMarkup(
        WordBundleParser.WordContext ctx
    ) {
        this.ctx = ctx;
    }

    @Override
    public String conceptionId() {
        return this.ctx.conception().CONCEPTION_ID().getText();
    }

    @Override
    public Stream<String> persistentGrammemes() {
        return this.ctx
            .persistent_grammemes()
            .grammemes()
            .GRAMMEME()
            .stream()
            .map(TerminalNode::getText);
    }

    @Override
    public Stream<WordFormMarkup> wordForms() {
        return this.ctx
            .word_forms()
            .entry()
            .stream()
            .map(ParsedWordFormMarkup::new);
    }
}
