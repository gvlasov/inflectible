package org.tendiwa.inflectible.implementations;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public final class RussianTest {
    @Test
    public void createsGrammar() throws Exception {
        MatcherAssert.assertThat(
            new Russian().grammar().grammemeByName("III"),
            CoreMatchers.equalTo(Russian.Grammemes.III)
        );
    }
}
