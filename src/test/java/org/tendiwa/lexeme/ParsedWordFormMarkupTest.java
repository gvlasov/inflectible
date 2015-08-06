package org.tendiwa.lexeme;

import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * @since 0.1
 */
public final class ParsedWordFormMarkupTest {
    @Test
    public void seesAllComponents() throws Exception {
        final WordBundleLexer lexer = lexerInLexemesMode("dudes [Plur]");
        lexer.mode(WordBundleLexer.LEXEMES);
        final WordFormMarkup markup = new ParsedWordFormMarkup(
            new WordBundleParser(
                new CommonTokenStream(lexer)
            )
                .entry()
        );
        MatcherAssert.assertThat(
            markup.spelling(),
            CoreMatchers.is("dudes")
        );
        MatcherAssert.assertThat(
            markup.grammemes()
                .collect(Collectors.toImmutableList())
                .contains("Plur"),
            CoreMatchers.is(true)
        );
    }

    /**
     * Constructs a lexer that starts in {@link WordBundleLexer#LEXEMES} mode.
     * @param input Text to parse.
     * @return Lexer in {@link WordBundleLexer#LEXEMES} mode.
     */
    private WordBundleLexer lexerInLexemesMode(String input)
        throws IOException {
        return new WordBundleLexer(
            new ANTLRInputStream(
                IOUtils.toInputStream(input)
            )
        );
    }

}
