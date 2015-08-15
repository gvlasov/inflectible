package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

public final class BasicPlaceholderTest {
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

    @Test
    public void overridesDefaults() throws Exception {
        final BasicPlaceholder thirdPersonPlaceholder =
            new BasicPlaceholder(new ArgumentsLexemeSource("action"))
                .withCapitalization(Capitalization.CAPITALZES)
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
}
