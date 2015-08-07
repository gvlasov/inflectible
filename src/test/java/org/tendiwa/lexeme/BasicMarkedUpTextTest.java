package org.tendiwa.lexeme;

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
        MatcherAssert.assertThat(
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
                .walk(Placeholder::id),
            CoreMatchers.equalTo("Dude and dudette once ate a taco.")
        );
    }
}
