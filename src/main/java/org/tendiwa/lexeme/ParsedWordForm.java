package org.tendiwa.lexeme;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedWordForm implements WordForm {
    private final Grammar grammar;
    private final WordBundleParser.EntryContext ctx;

    ParsedWordForm(Grammar grammar, WordBundleParser.EntryContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String spelling() {
        return this.ctx.WORD_FORM().getText();
    }

    @Override
    public int similarity(ImmutableSet<Grammeme> grammemes) {
        return Sets.intersection(
            grammemes,
            this.grammemes()
        ).size();
    }

    private ImmutableSet<Grammeme> grammemes() {
        if (this.ctx.grammemes() == null) {
            return ImmutableSet.of();
        } else {
            return this.ctx.grammemes().GRAMMEME()
                .stream()
                .map(TerminalNode::getText)
                .map(this.grammar::grammemeByName)
                .collect(Collectors.toImmutableSet());
        }
    }
}
