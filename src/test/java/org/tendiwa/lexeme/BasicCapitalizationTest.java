package org.tendiwa.lexeme;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class BasicCapitalizationTest {
    @Test
    public void decapitalizesArgumentName() throws Exception {
        MatcherAssert.assertThat(
            new BasicCapitalization("Dudeman").uncapitalized(),
            CoreMatchers.equalTo("dudeman")
        );
    }

    @Test
    public void leavesDecapitalizedNameDecapitalized() throws Exception {
        MatcherAssert.assertThat(
            new BasicCapitalization("mary").uncapitalized(),
            CoreMatchers.equalTo("mary")
        );
    }

    @Test
    public void seesCaseOfTheFirstLetter() throws Exception {
        MatcherAssert.assertThat(
            new BasicCapitalization("Helen").isFirstLetterCapital(),
            CoreMatchers.is(true)
        );
        MatcherAssert.assertThat(
            new BasicCapitalization("helen").isFirstLetterCapital(),
            CoreMatchers.is(false)
        );
    }
}
