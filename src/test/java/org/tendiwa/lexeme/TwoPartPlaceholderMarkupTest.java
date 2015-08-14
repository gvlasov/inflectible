package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

import java.util.stream.IntStream;

public final class TwoPartPlaceholderMarkupTest {
    @Test
    public void decapitalizesId() throws Exception {
        Assert.assertEquals(
            "man",
            new TwoPartPlaceholderMarkup(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[Man][These Dont Matter]"
                ).twoPartPlaceholder()
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
                ).twoPartPlaceholder()
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
                ).twoPartPlaceholder()
            ).agreementId().get()
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final TwoPartPlaceholderMarkup markup = new TwoPartPlaceholderMarkup(
            new TextBundleParserFactory().createInMode(
                TextBundleLexer.LINE_CONTENT,
                "[action][Plur]"
            ).twoPartPlaceholder()
        );
        IntStream.range(0, 2).forEach(
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
