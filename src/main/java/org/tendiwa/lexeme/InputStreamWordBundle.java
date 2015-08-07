package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tendiwa.rocollections.ReadOnlyList;
import org.tendiwa.rocollections.WrappingReadOnlyList;
import org.tenidwa.collections.utils.Collectors;

/**
 * WordBundle that parses an InputStream to get words.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class InputStreamWordBundle implements WordBundle {


    private final ReadOnlyList<LexemeMarkup> lexemes;

    InputStreamWordBundle(InputStream input) {
        this.lexemes = new WrappingReadOnlyList<>(
            this.createParser(input)
                .word_bundle()
                .word()
                .stream()
                .map(ctx -> (LexemeMarkup) new ParsedLexemeMarkup(ctx))
                .collect(Collectors.toImmutableList())
        );
    }

    @Override
    public ReadOnlyList<LexemeMarkup> words() {
        return this.lexemes;
    }


    /**
     * @param input Input stream with markup.
     * @return Most basic ANTLR parser for markup.
     */
    private WordBundleParser createParser(InputStream input) {
        try {
            return new WordBundleParser(
                new CommonTokenStream(
                    new WordBundleLexer(
                        new ANTLRInputStream(input)
                    )
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
