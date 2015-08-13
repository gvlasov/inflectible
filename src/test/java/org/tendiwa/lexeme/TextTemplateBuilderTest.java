package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.stream.IntStream;

public final class TextTemplateBuilderTest {
    @Test
    public void createsTemplate() throws Exception {
        MatcherAssert.assertThat(
            new TextTemplateBuilder(ImmutableList.of("subject", "object"))
                .addPlaceholder(new BasicPlaceholder("subject"))
                .addText(" immediately picks up an ")
                .addPlaceholder(new BasicPlaceholder("object"))
                .build()
            .fillUp(
                ImmutableList.<Lexeme>of(
                    new SingleFormLexeme("man"),
                    new SingleFormLexeme("apple")
                )
            ),
            CoreMatchers.equalTo("man immediately picks up an apple")
        );
    }

    @Test(expected = IllegalStateException.class)
    public void cantBeUsedTwice() throws Exception {
        final TextTemplateBuilder builder =
            new TextTemplateBuilder(ImmutableList.of("subject"))
                .addPlaceholder(new BasicPlaceholder("subject"));
        IntStream.range(0, 2).forEach(i -> builder.build());
    }

    @Test
    public void canConsistOfJustText() throws Exception {
        MatcherAssert.assertThat(
            new TextTemplateBuilder(ImmutableList.<String>of())
                .addText("Hey, ")
                .addText("dude!")
                .build()
                .fillUp(ImmutableList.of()),
            CoreMatchers.equalTo("Hey, dude!")
        );
    }
}
