package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;

/**
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholderTest {
    @Test
    public void capitalizesWordWhenNecessary() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormInsertionResult("[Dude]", "dude", "programmer"),
            CoreMatchers.equalTo("Programmer")
        );
    }

    @Test
    public void doesntCapitalizeWhenUnnecessary() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormInsertionResult("[dude]", "dude", "director"),
            CoreMatchers.equalTo("director")
        );
    }

    private String wordFormInsertionResult(
        String placeholderMarkup,
        String argumentName,
        String wordForm
    ) {
        return
            new ParsedSinglePartPlaceholder(
                new TemplateBundleParserFactory()
                    .createInMode(
                        TemplateBundleLexer.LINE_CONTENT,
                        placeholderMarkup
                    )
                    .singlePartPlaceholder()
            )
                .fillUp(
                    ImmutableMap.of(
                        argumentName,
                        new SingleFormLexeme(wordForm)
                    ),
                    ImmutableMap.of()
                );
    }
}
