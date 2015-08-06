package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
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
        return null;
    }

    @Override
    public ImmutableList<String> persistentGrammemes() {
        return null;
    }

    @Override
    public ImmutableList<WordFormMarkup> wordForms() {
        return null;
    }
}
