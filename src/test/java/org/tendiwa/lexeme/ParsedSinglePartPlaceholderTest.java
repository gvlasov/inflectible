package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

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
                new TextBundleParserFactory()
                    .createInMode(
                        TextBundleLexer.LINE_CONTENT,
                        placeholderMarkup
                    )
                    .base_form_placeholder()
            )
                .fillUp(
                    ImmutableMap.of(
                        argumentName,
                        new SingleFormLexeme(wordForm)
                    )
                );
    }
}
