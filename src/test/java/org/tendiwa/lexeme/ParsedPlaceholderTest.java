package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

public final class ParsedPlaceholderTest {
    @Test
    public void decapitalizesId() throws Exception {
        Assert.assertEquals(
            "man",
            new ParsedPlaceholder(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[Man]"
                ).placeholder()
            ).id()
        );
    }

    @Test
    public void findsGrammemes() throws Exception {
        Assert.assertEquals(
            ImmutableList.of("Sing", "Masculine"),
            new ParsedPlaceholder(
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
            new ParsedPlaceholder(
                new TextBundleParserFactory().createInMode(
                    TextBundleLexer.LINE_CONTENT,
                    "[action][Plur;woman]"
                ).placeholder()
            ).agreementId().get()
        );
    }

}
