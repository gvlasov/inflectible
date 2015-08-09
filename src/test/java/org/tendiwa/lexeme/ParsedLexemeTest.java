package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;

/**
 * @since 0.1
 */
public final class ParsedLexemeTest {
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words")
                .baseForm(),
            CoreMatchers.equalTo("bear")
        );
    }


    @Test
    public void worksWithZeroPersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words")
                .persistentGrammemes(),
            CoreMatchers.equalTo(ImmutableSet.of())
        );
    }

    @Test
    public void canAssumeCorrectForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words")
                .form(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo("bears")
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final ParsedLexeme lexeme =
            this.firstWordOfBundle("characters.en_US.words");
        IntStream.range(0, 1).forEach(
            i->
                MatcherAssert.assertThat(
                    lexeme.baseForm(),
                    CoreMatchers.equalTo("bear")
                )
        );
    }

    private ParsedLexeme firstWordOfBundle(
        String resourceName
    ) {
        return new ParsedLexeme(
            new English().grammar(),
            new BasicWordBundleParser(
                ParsedLexemeTest.class.getResourceAsStream(
                    resourceName
                )
            )
                .word_bundle()
                .word()
                .get(0)
        );
    }
}
