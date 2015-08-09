package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;

/**
 * @since 0.1
 */
public final class ParsedWordFormTest {
    @Test
    public void hasSpelling() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears().spelling(),
            CoreMatchers.equalTo("bears")
        );
    }

    @Test
    public void computesSimilarity() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears()
                .similarity(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo(1)
        );
    }

    private ParsedWordForm wordFormBears() {
        return new ParsedWordForm(
            new English().grammar(),
            new BasicWordBundleParser(
                ParsedWordFormTest.class.getResourceAsStream(
                    "characters.en_US.words"
                )
            )
                .word_bundle()
                .word()
                .get(0)
                .word_forms()
                .entry()
                .get(1)
        );
    }
}
