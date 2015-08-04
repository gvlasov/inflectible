package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * WordBundleEntry extracted from a parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicWordBundleEntry implements WordBundleEntry {
    private final Grammar grammar;
    private final WordBundleParser.WordContext wordCtx;

    public BasicWordBundleEntry(
        Grammar grammar,
        WordBundleParser.WordContext wordCtx
    ) {
        this.grammar = grammar;
        this.wordCtx = wordCtx;
    }

    @Override
    public String conceptionId() {
        return this.wordCtx.conception().CONCEPTION_ID().getText();
    }

    @Override
    public Lexeme lexeme() {
        return new BasicLexeme(
            this.persistentGrammemes(),
            this.formsWithGrammemes()
        );
    }

    private List<LexemeEntry> formsWithGrammemes() {
        return this.wordCtx
            .word_forms()
            .entry()
            .stream()
            .map(
                entryCtx -> new BasicLexemeEntry(
                    this.grammar,
                    entryCtx
                )
            )
            .collect(Collectors.toImmutableList());
    }

    private ImmutableSet<Grammeme> persistentGrammemes() {
        return this.wordCtx
            .persistent_grammemes()
            .grammemes()
            .GRAMMEME()
            .stream()
            .map(TerminalNode::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }
}
