package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @since 0.1
 */
public final class BasicMarkedUpTextTest {

    /**
     * BasicMarkedUpText knows its id.
     */
    @Test
    public void knowsItsId() {
        Assert.assertEquals(
            "action.act",
            new BasicMarkedUpText(
                new TextBundleParserFactory()
                    .create(
                        "action.act(actor, seer) {",
                        "  [Actor][;seer]. And then [seer][;actor]",
                        "}"
                    )
                    .text()
            )
                .id()
        );
    }

    @Test
    public void hasWalkableBody() {
        final UnwrappingWalker walker = new UnwrappingWalker();
        new BasicMarkedUpText(
            new TextBundleParserFactory()
                .create(
                    "story.short(dude, dudette) {",
                    "  [Dude] and [dudette] once ate a taco.",
                    "}"
                )
                .text()
        )
            .body()
            .walk(walker);
        MatcherAssert.assertThat(
            walker.joined(),
            CoreMatchers.equalTo("Dude and dudette once ate a taco.")
        );
    }

    /**
     * A BodyWalker that joins placeholder IDs and plaintext together into a
     * single string.
     */
    private static final class UnwrappingWalker implements BodyWalker {

        final Collection<String> parts = new ArrayList<>();

        @Override
        public void enterPlaceholder(Placeholder placeholder) {
            parts.add(placeholder.id());
        }

        @Override
        public void enterPlaintext(String plainText) {
            parts.add(plainText);
        }

        public String joined() {
            return Joiner.on("").join(parts);
        }
    }
}
