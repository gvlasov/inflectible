package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;
import org.tendiwa.lexeme.implementations.Russian;

/**
 * @since 0.1
 */
public final class ParsedLexemeTest {
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words", new English())
                .baseForm(),
            CoreMatchers.equalTo("bear")
        );
    }


    @Test
    public void hasPersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.ru_RU.words", new Russian())
                .persistentGrammemes(),
            CoreMatchers.equalTo(ImmutableSet.of(Russian.Grammemes.Муж))
        );
    }

    @Test
    public void canAssumeCorrectForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .firstWordOfBundle("characters.en_US.words", new English())
                .form(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo("bears")
        );
    }

    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final ParsedLexeme lexeme =
            this.firstWordOfBundle("characters.en_US.words", new English());
        IntStream.range(0, 1).forEach(
            i->
                MatcherAssert.assertThat(
                    lexeme.baseForm(),
                    CoreMatchers.equalTo("bear")
                )
        );
    }

    private ParsedLexeme firstWordOfBundle(
        String resourceName,
        Language language
    ) {
        return new ParsedLexeme(
            language.grammar(),
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
