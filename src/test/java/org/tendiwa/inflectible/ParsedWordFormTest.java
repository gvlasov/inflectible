package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedWordForm}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedWordFormTest {
    /**
     * ParsedWordForm can obtain its spelling from an ANTLR parse tree.
     * @throws Exception If fails
     */
    @Test
    public void hasSpelling() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears().spelling(),
            CoreMatchers.equalTo("bears")
        );
    }

    /**
     * ParsedWordForm can tell how similar its grammatical meaning is to some
     * other grammatical meaning.
     * @throws Exception If fails
     */
    @Test
    public void computesSimilarity() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears()
                .similarity(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo(1)
        );
    }

    /**
     * Creates a plural word form <i>bears</i>.
     * @return Plural word form <i>bears</i>
     */
    private ParsedWordForm wordFormBears() {
        return new ParsedWordForm(
            new English().grammar(),
            new BasicLexemeBundleParser(
                "BEAR {",
                "  bear",
                "  bears [Plur]",
                "}"
            )
                .lexemes()
                .lexeme(0)
                .wordForms()
                .wordForm(1)
        );
    }
}
