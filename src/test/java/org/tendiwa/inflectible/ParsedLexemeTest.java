package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

import java.util.stream.IntStream;

/**
 * @since 0.1
 */
public final class ParsedLexemeTest {
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .defaultSpelling(),
            CoreMatchers.equalTo("bear")
        );
    }

    @Test
    public void worksWithZeroPersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .persistentGrammemes(),
            CoreMatchers.equalTo(ImmutableSet.of())
        );
    }

    @Test
    public void canAssumeCorrectForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .wordForm(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo("bears")
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final ParsedLexeme lexeme =
            this.wordOfBundle("characters.en_US.words", 0);
        IntStream.range(0, 2).forEach(
            i ->
                MatcherAssert.assertThat(
                    lexeme.defaultSpelling(),
                    CoreMatchers.equalTo("bear")
                )
        );
    }

    @Test
    public void canHavePersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this.wordOfBundle("characters.en_US.words", 2)
                .persistentGrammemes().size(),
            CoreMatchers.equalTo(1)
        );
    }

    private ParsedLexeme wordOfBundle(String resourceName, int index) {
        return new ParsedLexeme(
            new English().grammar(),
            new BasicLexemeBundleParser(
                ParsedLexemeTest.class.getResourceAsStream(
                    resourceName
                )
            )
                .lexemes()
                .lexeme(index)
        );
    }
}
