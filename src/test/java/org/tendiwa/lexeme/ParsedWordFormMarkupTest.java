package org.tendiwa.lexeme;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * @since 0.1
 */
public final class ParsedWordFormMarkupTest {
    @Test
    public void seesAllComponents() throws Exception {
        final WordFormMarkup markup = new ParsedWordFormMarkup(
            new WordBundleParser(
                new CommonTokenStream(
                    new WordBundleLexer(
                        new ANTLRInputStream(
                            IOUtils.toInputStream(
                                "  dudes [Plur]"
                            )
                        )
                    )
                )
            )
                .entry()
        );
        MatcherAssert.assertThat(
            markup.spelling(),
            CoreMatchers.is("dudes")
        );
        MatcherAssert.assertThat(
            markup.grammemes().contains("Plur"),
            CoreMatchers.is(true)
        );
    }

}
