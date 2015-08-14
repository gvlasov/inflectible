package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;

import java.util.stream.IntStream;

/**
 * @since 0.1
 */
public final class ParsedLexemeTest {
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words")
                .defaultSpelling(),
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
                .wordForm(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo("bears")
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final ParsedLexeme lexeme =
            this.firstWordOfBundle("characters.en_US.words");
        IntStream.range(0, 2).forEach(
            i->
                MatcherAssert.assertThat(
                    lexeme.defaultSpelling(),
                    CoreMatchers.equalTo("bear")
                )
        );
    }

    private ParsedLexeme firstWordOfBundle(
        String resourceName
    ) {
        return new ParsedLexeme(
            new English().grammar(),
            new BasicLexemeBundleParser(
                ParsedLexemeTest.class.getResourceAsStream(
                    resourceName
                )
            )
                .lexemes()
                .lexeme(0)
        );
    }
}
