package org.tendiwa.lexeme;

import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

/**
 * @since 0.1
 */
public final class IdOnlyPlaceholderMarkupTest {

    @Test
    public void hasNoExplicitGrammaticalMeaning() throws Exception {
        MatcherAssert.assertThat(
            this.placeholder("hey").explicitGrammemes().size(),
            CoreMatchers.is(0)
        );
    }

    @Test
    public void hasNoAgreement() throws Exception {
        MatcherAssert.assertThat(
            this.placeholder("hello").agreementId().isPresent(),
            CoreMatchers.is(false)
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        IntStream.range(0, 3).forEach(
            (i) ->
                MatcherAssert.assertThat(
                    this.placeholder("dude").argumentName(),
                    CoreMatchers.is("dude")
                )
        );
    }

    @Test
    public void canHandleCapitalizedId() throws Exception {
        MatcherAssert.assertThat(
            this.placeholder("Man").argumentName(),
            CoreMatchers.equalTo("man")
        );
    }

    private IdOnlyPlaceholderMarkup placeholder(String id) {
        return new IdOnlyPlaceholderMarkup(
            new TextBundleParserFactory()
                .createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    String.format(
                        "[%s]",
                        id
                    )
                )
                .base_form_placeholder()
        );
    }

}
