package org.tendiwa.lexeme;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @since 0.1
 */
public final class EnumBasedGrammarTest {
    @Test
    public void findsGrammemeByName() {
        MatcherAssert.assertThat(
            new EnumBasedGrammar(KobaianGrammemes.class)
                .grammemeByName("Default"),
            CoreMatchers.equalTo(KobaianGrammemes.Default)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void disallowsNonEnumGrammemes() {
        new EnumBasedGrammar(new Grammeme() {}.getClass());
    }

    private enum KobaianGrammemes implements Grammeme {
        Default
    }
}
