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
 */
public final class BasicPlaceholderTest {
    /**
     * BasicPlaceholder can be created with default aspects.
     * @throws Exception If fails
     */
    @Test
    public void fillsItselfUpWithDefaults() throws Exception {
        MatcherAssert.assertThat(
            new BasicPlaceholder(new VocabularyLexemeSource("bear"))
                .fillUp(
                    ImmutableMap.of(),
                    ImmutableMap.of("bear", new SingleFormLexeme("bear"))
                ),
            CoreMatchers.equalTo("bear")
        );
    }

    /**
     * BasicPlaceholder can create derivative BasicPlaceholders with
     * different placeholder aspects.
     * @throws Exception If fails
     */
    @Test
    public void overridesDefaults() throws Exception {
        final BasicPlaceholder thirdPersonPlaceholder =
            new BasicPlaceholder(new ArgumentsLexemeSource("action"))
                .withCapitalization(Capitalization.CAPITALIZE)
                .withExplicitGrammemes(
                    ImmutableSet.of(English.Grammemes.III)
                )
                .withAgreement(new ArgumentAgreement("actor"));
        final ImmutableMap<String, Lexeme> argumentsBearAndEat =
            ImmutableMap.<String, Lexeme>builder()
                .put(
                    "action",
                    new BasicLexeme(
                        ImmutableSet.of(),
                        ImmutableList.of(
                            new BasicWordForm("eat", ImmutableSet.of()),
                            new BasicWordForm(
                                "eats",
                                ImmutableSet.of(
                                    English.Grammemes.III,
                                    English.Grammemes.Sing
                                )
                            )
                        )
                    )
                )
                .put("actor", new SingleFormLexeme("bear"))
                .build();
        MatcherAssert.assertThat(
            thirdPersonPlaceholder.fillUp(
                argumentsBearAndEat,
                ImmutableMap.of()
            ),
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
        final BasicPlaceholder originalPlaceholder =
            new BasicPlaceholder(Mockito.mock(LexemeSource.class))
                .withAgreement(agreement)
                .withCapitalization(capitalization)
                .withExplicitGrammemes(explicitGrammemes);
        final BasicPlaceholder reconfiguredPlaceholder =
            originalPlaceholder
                .withCapitalization(capitalization)
                .withAgreement(agreement)
                .withExplicitGrammemes(explicitGrammemes);
        MatcherAssert.assertThat(
            originalPlaceholder,
            CoreMatchers.is(reconfiguredPlaceholder)
        );
    }
}
