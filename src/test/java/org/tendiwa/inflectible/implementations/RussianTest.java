package org.tendiwa.inflectible.implementations;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link Russian}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class RussianTest {
    /**
     * {@link Russian} can create its grammar.
     * @throws Exception If fails
     */
    @Test
    public void createsGrammar() throws Exception {
        MatcherAssert.assertThat(
            new Russian().grammar().grammemeByName("III"),
            CoreMatchers.equalTo(Russian.Grammemes.III)
        );
    }
}
