package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @since 0.1
 */
public final class ParsedDeclaredArgumentsTest {
    @Test
    public void findsIndexOfArgument() throws Exception {
        MatcherAssert.assertThat(
            new ParsedDeclaredArguments(
                new TextBundleParserFactory().create(
                    Joiner.on('\n').join(
                        "hey(a, b, c) {",
                        " [A][Plur] [b] [c]",
                        "}"
                    )
                ).text_bundle().text().get(0)
            ).index("b"),
            CoreMatchers.equalTo(1)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsIfMissingArgumentIsAccessed() throws Exception {
        new ParsedDeclaredArguments(
            new TextBundleParserFactory().create(
                Joiner.on('\n').join(
                    "hey(a, b) {",
                    " [A][Plur] [b]",
                    "}"
                )
            ).text_bundle().text().get(0)
        ).index("c");
    }
}
