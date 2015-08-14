package org.tendiwa.lexeme;


import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.LexemeBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedWordForm implements WordForm {
    private final Grammar grammar;
    private final LexemeBundleParser.WordFormContext ctx;

    ParsedWordForm(Grammar grammar, LexemeBundleParser.WordFormContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String spelling() {
        return this.delegate().spelling();
    }

    @Override
    public int similarity(ImmutableSet<Grammeme> grammemes) {
        return this.delegate().similarity(grammemes);
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

    private String wordSpelling() {
        return this.ctx.WORD_FORM().getText();
    }

    // TODO: To be refactored in #47
    private WordForm delegate() {
        return new BasicWordForm(this.wordSpelling(), this.grammemes());
    }
}
