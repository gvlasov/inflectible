package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.stream.IntStream;

public final class TextTemplateBuilderTest {
    @Test
    public void createsTemplate() throws Exception {
        MatcherAssert.assertThat(
            new TextTemplateBuilder(ImmutableList.of("subject", "object"))
                .addPlaceholder(
                    new BasicPlaceholder(
                        new ArgumentsLexemeSource("subject")
                    )
                )
                .addText(" immediately picks up an ")
                .addPlaceholder(
                    new BasicPlaceholder(
                        new ArgumentsLexemeSource("object")
                    )
                )
                .build()
            .fillUp(
                ImmutableList.<Lexeme>of(
                    new SingleFormLexeme("man"),
                    new SingleFormLexeme("apple")
                ),
                ImmutableMap.of()
            ),
            CoreMatchers.equalTo("man immediately picks up an apple")
        );
    }

    @Test(expected = IllegalStateException.class)
    public void cantBeUsedTwice() throws Exception {
        final TextTemplateBuilder builder =
            new TextTemplateBuilder(ImmutableList.of("subject"))
                .addPlaceholder(
                    new BasicPlaceholder(
                        new ArgumentsLexemeSource("subject")
                    )
                );
        IntStream.range(0, 2).forEach(i -> builder.build());
    }

    @Test
    public void canConsistOfJustText() throws Exception {
        MatcherAssert.assertThat(
            new TextTemplateBuilder(ImmutableList.<String>of())
                .addText("Hey, ")
                .addText("dude!")
                .build()
                .fillUp(ImmutableList.of(), ImmutableMap.of()),
            CoreMatchers.equalTo("Hey, dude!")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void createdTextsReportWrongNumberOfArguments() throws Exception {
        new TextTemplateBuilder(ImmutableList.of("subject", "object"))
            .build()
            .fillUp(
                ImmutableList.of(
                    new SingleFormLexeme("Mary"),
                    new SingleFormLexeme("cock"),
                    new SingleFormLexeme("backstage")
                ),
                ImmutableMap.of()
            );
    }
}
