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
 */
public final class BasicNativeSpeakerTest {
    /**
     * BasicNativeSpeaker can fill out a text.
     * @throws Exception If fails
     */
    @Test
    public void fillsOutText() throws Exception {
        MatcherAssert.assertThat(
            new BasicNativeSpeaker(
                ImmutableMap.of("bear", new SingleFormLexeme("bear")),
                ImmutableMap.of(
                    "attack.bite",
                    new TextTemplateBuilder(ImmutableList.of("attacker"))
                        .addPlaceholder(
                            new BasicPlaceholder(
                                new ArgumentsLexemeSource("attacker")
                            )
                                .withCapitalization(Capitalization.CAPITALIZE)
                        )
                        .addText(" furiously bites YOU!")
                        .build()
                )
            ).text("attack.bite", () -> "bear"),
            CoreMatchers.equalTo("Bear furiously bites YOU!")
        );
    }
}
