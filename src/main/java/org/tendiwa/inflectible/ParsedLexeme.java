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

    /**
     * Grammar of the language of this lexeme.
     */
    private final Grammar grammar;

    /**
     * ANTLR parse tree of a lexeme.
     */
    private final LexemeBundleParser.LexemeContext ctx;

    /**
     * Ctor.
     * @param parseTree ANTLR parse tree of a lexeme
     */
    public ParsedLexeme(
        Grammar grammar,
        LexemeBundleParser.LexemeContext parseTree
    ) {
        this.grammar = grammar;
        this.ctx = parseTree;
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

    /**
     * Creates a lexeme with word forms and persistent grammatical meaning
     * obtained from the {@link ParsedLexeme#ctx}.
     * @return Lexeme from markup.
     */
    private Lexeme delegate() {
        return new BasicLexeme(
            this.grammemes(),
            this.wordForms()
        );
    }

    /**
     * Obtains grammemes from markup.
     * @return Grammemes.
     */
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

    /**
     * Obtains word forms from markup.
     * @return Word forms.
     */
    private ImmutableList<WordForm> wordForms() {
        return this.ctx
            .wordForms()
            .wordForm()
            .stream()
            .map(ctx->new ParsedWordForm(this.grammar, ctx))
            .collect(Collectors.toImmutableList());
    }
}
