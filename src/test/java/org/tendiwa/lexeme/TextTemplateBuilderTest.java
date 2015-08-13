package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public final class TextTemplateBuilderTest {
    @Test
    public void createsTemplate() {
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
}
