package org.tendiwa.inflectible;


import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link WordForm} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedWordForm implements WordForm {
    /**
     * Grammar of the language of this word form.
     */
    private final Grammar grammar;

    /**
     * ANTLR parse tree of this word from.
     */
    private final LexemeBundleParser.WordFormContext ctx;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of this word form
     * @param parseTree ANTLR parse tree of this word form
     */
    ParsedWordForm(
        Grammar grammemes,
        LexemeBundleParser.WordFormContext parseTree
    ) {
        this.grammar = grammemes;
        this.ctx = parseTree;
    }

    @Override
    public String spelling() {
        return this.delegate().spelling();
    }

    @Override
    public int similarity(ImmutableSet<Grammeme> grammemes) {
        return this.delegate().similarity(grammemes);
    }

    /**
     * Obtains grammemes from the ANTLR parse tree.
     * @return Grammemes of this word form
     */
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

    /**
     * Obtains spelling of this word form from the ANTLR parse tree.
     * @return Spelling of this word form
     */
    private String wordSpelling() {
        return this.ctx.WORD_FORM().getText();
    }

    /**
     * Creates a word form from the {@link ParsedWordForm#ctx}.
     * @return Word form
     */
    // TODO: To be refactored in #47
    private WordForm delegate() {
        return new BasicWordForm(this.wordSpelling(), this.grammemes());
    }
}
