package org.tendiwa.lexeme;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * @since 0.1
 */
public final class ParsedLexemeMarkupTest {
    @Test
    public void handlesMissingPersistentGrammemes() throws Exception {
        final WordBundleParser parser = new WordBundleParserFactory().create(
            "\"human\" {",
            "  human",
            "  humans [Plur]",
            "}"
        );
        MatcherAssert.assertThat(
            new ParsedLexemeMarkup(
                parser.word()
            ).persistentGrammemes().isEmpty(),
            CoreMatchers.is(true)
        );
    }

    @Test
    public void seesPresentPersistentGrammemes() throws Exception {
        final WordBundleParser parser = new WordBundleParserFactory().create(
            "\"human\" [Masculine] {",
            "  human",
            "  humans [Plur]",
            "}"
        );
        MatcherAssert.assertThat(
            new ParsedLexemeMarkup(
                parser.word()
            ).persistentGrammemes().get(0),
            CoreMatchers.is("Masculine")
        );
    }
}
