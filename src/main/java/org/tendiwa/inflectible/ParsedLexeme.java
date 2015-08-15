package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link Lexeme} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedLexeme implements Lexeme {

    private final Grammar grammar;

    private final LexemeBundleParser.LexemeContext ctx;

    /**
     * @param ctx ANTLR parse tree of a lexeme
     */
    public ParsedLexeme(Grammar grammar, LexemeBundleParser.LexemeContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String wordForm(ImmutableSet<Grammeme> grammaticalMeaning) {
        return this.delegate().wordForm(grammaticalMeaning);
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.delegate().persistentGrammemes();
    }

    @Override
    public String defaultSpelling() {
        return this.delegate().defaultSpelling();
    }

    private Lexeme delegate() {
        return new BasicLexeme(
            this.grammemes(),
            this.wordForms()
        );
    }

    private ImmutableSet<Grammeme> grammemes() {
        final LexemeBundleParser.PersistentGrammemesContext persistent =
            this.ctx.persistentGrammemes();
        if (persistent == null) {
            return ImmutableSet.of();
        } else {
            return persistent
                .grammemes()
                .GRAMMEME()
                .stream()
                .map(TerminalNode::getText)
                .map(this.grammar::grammemeByName)
                .collect(Collectors.toImmutableSet());
        }
    }

    private ImmutableList<WordForm> wordForms() {
        return this.ctx
            .wordForms()
            .wordForm()
            .stream()
            .map(ctx->new ParsedWordForm(this.grammar, ctx))
            .collect(Collectors.toImmutableList());
    }
}
