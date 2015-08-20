/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Georgy Vlasov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests for {@link TextTemplateBuilder}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class TemplateBuilderTest {
    /**
     * TextTemplateBuilder can create a template.
     * @throws Exception If fails
     */
    @Test
    public void createsTemplate() throws Exception {
        final ArgumentName subject = new ArgumentName("subject");
        final ArgumentName object = new ArgumentName("object");
        MatcherAssert.assertThat(
            new TextTemplateBuilder(
                ImmutableList.of(
                    subject,
                    object
                )
            )
                .addPlaceholder(
                    new PhFromArgument(subject)
                )
                .addText(" immediately picks up an ")
                .addPlaceholder(
                    new PhFromArgument(object)
                )
                .build()
                .fillUp(
                    ImmutableList.<Lexeme>of(
                        new SingleFormLexeme("man"),
                        new SingleFormLexeme("apple")
                    ),
                    new BasicVocabulary()
                ),
            CoreMatchers.equalTo("man immediately picks up an apple")
        );
    }

    /**
     * TextTemplateBuilder can not be used twice to produce a template.
     * @throws Exception If fails
     */
    @Test(expected = IllegalStateException.class)
    public void cantBeUsedTwice() throws Exception {
        final ArgumentName name = new ArgumentName("actor");
        final TextTemplateBuilder builder =
            new TextTemplateBuilder(ImmutableList.of(name))
                .addPlaceholder(
                    new PhFromArgument(name)
                );
        IntStream.range(0, 2).forEach(i -> builder.build());
    }

    /**
     * TextTemplateBuilder can consist of just text, with no placeholders.
     * @throws Exception If fails
     */
    @Test
    public void canConsistOfJustText() throws Exception {
        MatcherAssert.assertThat(
            new TextTemplateBuilder(ImmutableList.of())
                .addText("Hey, ")
                .addText("dude!")
                .build()
                .fillUp(ImmutableList.of(), new BasicVocabulary()),
            CoreMatchers.equalTo("Hey, dude!")
        );
    }

    /**
     * TextTemplateBuilder can create texts that report wrong number of
     * arguments.
     * @throws Exception If can't fill up the template
     */
    @Test(expected = Exception.class)
    @Ignore
    public void createdTextsReportWrongNumberOfArguments() throws Exception {
        new TextTemplateBuilder(
            ImmutableList.of(
                new ArgumentName("someone"),
                new ArgumentName("item")
            )
        )
            .build()
            .fillUp(
                ImmutableList.of(
                    new SingleFormLexeme("Mary"),
                    new SingleFormLexeme("cock"),
                    new SingleFormLexeme("backstage")
                ),
                new BasicVocabulary()
            );
    }
}
