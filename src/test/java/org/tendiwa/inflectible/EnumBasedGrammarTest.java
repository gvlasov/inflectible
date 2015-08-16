package org.tendiwa.inflectible;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link EnumBasedGrammar}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class EnumBasedGrammarTest {
    /**
     * EnumBasedGrammar can return a grammeme by its name.
     * @throws Exception If fails
     */
    @Test
    public void findsGrammemeByName() throws Exception {
        MatcherAssert.assertThat(
            new EnumBasedGrammar(KobaianGrammemes.class)
                .grammemeByName("Default"),
            CoreMatchers.equalTo(KobaianGrammemes.Default)
        );
    }

    /**
     * EnumBasedGrammar can fail if a class that is not an enum is provided
     * to its constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void disallowsNonEnumGrammemes() {
        new EnumBasedGrammar(new Grammeme() {}.getClass());
    }

    /**
     * A simple grammar for a Kobaian language.
     */
    private enum KobaianGrammemes implements Grammeme {
        Default
    }
}
