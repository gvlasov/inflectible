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
import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link BasicPlaceholder}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (3 lines)
 */
public final class BasicPlaceholderTest {
    /**
     * BasicPlaceholder can be created with default aspects.
     * @throws Exception If fails
     */
    @Test
    public void fillsItselfUpWithDefaults() throws Exception {
        final LexemeName name = new LexemeName("BEAR");
        final String form = "bear";
        MatcherAssert.assertThat(
            new BasicPlaceholder(
                new VocabularyLexemeSource(
                    name
                )
            )
                .fillUp(
                    new BasicActualArguments(),
                    new BasicVocabulary(
                        ImmutableMap.of(name, new SingleFormLexeme(form))
                    )
                ),
            CoreMatchers.equalTo(form)
        );
    }

    /**
     * BasicPlaceholder can create derivative BasicPlaceholders with
     * different placeholder aspects.
     * @throws Exception If fails
     */
    @Test
    public void overridesDefaults() throws Exception {
        final String actor = "actor";
        final String action = "action";
        final BasicPlaceholder third =
            new BasicPlaceholder(
                new ArgumentsLexemeSource(
                    new ArgumentName(action)
                )
            )
                .withCapitalization(Capitalization.CAPITALIZE)
                .withExplicitGrammemes(
                    ImmutableSet.of(English.Grammemes.III)
                )
                .withAgreement(
                    new ArgumentAgreement(
                        new ArgumentName(actor)
                    )
                );
        final ActualArguments arguments =
            new BasicActualArguments(
                ImmutableList.of(
                    new ArgumentName(action),
                    new ArgumentName(actor)
                ),
                ImmutableList.of(
                    new BasicLexeme(
                        ImmutableSet.of(),
                        ImmutableList.of(
                            new BasicWordForm(
                                new BasicSpelling("eat"),
                                ImmutableSet.of()
                            ),
                            new BasicWordForm(
                                new BasicSpelling("eats"),
                                ImmutableSet.of(
                                    English.Grammemes.III,
                                    English.Grammemes.Sing
                                )
                            )
                        )
                    ),
                    new SingleFormLexeme("wolf")
                )
            );
        MatcherAssert.assertThat(
            third.fillUp(arguments, new BasicVocabulary()),
            CoreMatchers.equalTo("Eats")
        );
    }

    /**
     * BasicPlaceholder can return itself if the aspect set is the one that
     * this placeholder already has.
     * @throws Exception If fails
     */
    @Test
    public void sticksToExistingObjectWhenParametersSetToSame()
        throws Exception {
        final Agreement agreement = Mockito.mock(Agreement.class);
        final ImmutableSet<Grammeme> explicitGrammemes = ImmutableSet.of();
        final Capitalization capitalization =
            Mockito.mock(Capitalization.class);
        final BasicPlaceholder original =
            new BasicPlaceholder(Mockito.mock(LexemeSource.class))
                .withAgreement(agreement)
                .withCapitalization(capitalization)
                .withExplicitGrammemes(explicitGrammemes);
        final BasicPlaceholder reconfigured =
            original
                .withCapitalization(capitalization)
                .withAgreement(agreement)
                .withExplicitGrammemes(explicitGrammemes);
        MatcherAssert.assertThat(
            original,
            CoreMatchers.is(reconfigured)
        );
    }
}
