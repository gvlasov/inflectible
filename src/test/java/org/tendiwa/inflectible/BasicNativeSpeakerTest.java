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
import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link BasicNativeSpeaker}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (3 lines)
 */
public final class BasicNativeSpeakerTest {
    /**
     * BasicNativeSpeaker can fill out a text.
     * @throws Exception If fails
     */
    @Test
    public void fillsOutText() throws Exception {
        final ArgumentName argument = new ArgumentName("attacker");
        final String template = "attack.bite";
        final String lexeme = "BEAR";
        MatcherAssert.assertThat(
            new BasicNativeSpeaker(
                new BasicVocabulary(
                    ImmutableMap.of(lexeme, new SingleFormLexeme("bear"))
                ),
                new BasicTemplatuary(
                    ImmutableMap.of(
                        new TemplateName(template),
                        new TextTemplateBuilder(
                            ImmutableList.of(argument)
                        )
                            .addPlaceholder(
                                new BasicPlaceholder(
                                    new ArgumentsLexemeSource(
                                        argument
                                    )
                                )
                                    .withCapitalization(
                                        Capitalization.CAPITALIZE
                                    )
                            )
                            .addText(" furiously bites YOU!")
                            .build()
                    )
                )
            )
                .text(template, () -> lexeme),
            CoreMatchers.equalTo("Bear furiously bites YOU!")
        );
    }
}
