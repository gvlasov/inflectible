package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link Lexeme} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedLexeme implements Lexeme {

    private final Grammar grammar;
    private final WordBundleParser.WordContext ctx;

    /**
     * @param ctx ANTLR parse tree of a lexeme
     */
    public ParsedLexeme(Grammar grammar, WordBundleParser.WordContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String formForPlaceholder(Placeholder placeholder) {
        int bestScore = 0;
        WordForm bestMatch = null;
        for (WordForm form : this.wordForms()) {
            int score = form.similarity(placeholder.grammaticalMeaning());
            if (score > bestScore) {
                bestScore = score;
                bestMatch = form;
            }
        }
        if (bestMatch == null) {
            throw new IllegalArgumentException(
                String.format(
                    "Word form for grammatical meaning %s not found",
                    placeholder.grammaticalMeaning()
                )
            );
        }
        return bestMatch.spelling();
    }


    @Override
    public String baseForm() {
        return this.wordForms().get(0).spelling();
    }


    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        final WordBundleParser.Persistent_grammemesContext persistent =
            this.ctx.persistent_grammemes();
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
            .word_forms()
            .entry()
            .stream()
            .map(ctx->new ParsedWordForm(this.grammar, ctx))
            .collect(Collectors.toImmutableList());
    }
}
