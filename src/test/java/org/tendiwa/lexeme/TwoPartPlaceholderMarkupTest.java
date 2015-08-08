package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import java.util.stream.IntStream;
import junit.framework.Assert;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

public final class TwoPartPlaceholderMarkupTest {
    @Test
    public void decapitalizesId() throws Exception {
        Assert.assertEquals(
            "man",
            new TwoPartPlaceholderMarkup(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[Man][These Dont Matter]"
                ).placeholder()
            ).argumentName()
        );
    }

    @Test
    public void findsGrammemes() throws Exception {
        Assert.assertEquals(
            ImmutableList.of("Sing", "Masculine"),
            new TwoPartPlaceholderMarkup(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[Man][Sing Masculine]"
                ).placeholder()
            ).explicitGrammemes()
        );
    }

    @Test
    public void findsAgreementId() throws Exception {
        Assert.assertEquals(
            "woman",
            new TwoPartPlaceholderMarkup(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[action][Plur;woman]"
                ).placeholder()
            ).agreementId().get()
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final TwoPartPlaceholderMarkup markup = new TwoPartPlaceholderMarkup(
            new TextBundleParserFactory().createInMode(
                TextBundleLexer.LINE_CONTENT,
                "[action][Plur]"
            ).placeholder()
        );
        IntStream.range(0, 1).forEach(
            i ->
                MatcherAssert.assertThat(
                    markup.argumentName(),
                    CoreMatchers.equalTo("action")
                )
        );
        MatcherAssert.assertThat(
            markup.explicitGrammemes().contains("Plur"),
            CoreMatchers.equalTo(true)
        );
    }
}
