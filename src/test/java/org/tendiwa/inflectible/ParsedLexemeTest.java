package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

import java.util.stream.IntStream;

/**
 * Unit tests for {@link ParsedLexeme}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedLexemeTest {
    /**
     * ParsedLexeme can return its base form.
     * @throws Exception If fails
     */
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .defaultSpelling(),
            CoreMatchers.equalTo("bear")
        );
    }

    /**
     * ParsedLexeme can be created from an ANTLR parse tree with 0 persistent
     * grammemes in it.
     * @throws Exception If fails
     */
    @Test
    public void worksWithZeroPersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .persistentGrammemes(),
            CoreMatchers.equalTo(ImmutableSet.of())
        );
    }

    /**
     * ParsedLexeme can return the right word form based on grammemes
     * provided to it.
     * @throws Exception If fails
     */
    @Test
    public void canAssumeCorrectForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle("characters.en_US.words", 0)
                .wordForm(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo("bears")
        );
    }

    /**
     * ParsedLexeme can be used multiple times to return the same thing.
     * @throws Exception If fails
     */
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

    /**
     * ParsedLexeme can obtain persistent grammemes from an ANTLR parse tree.
     * @throws Exception If fails
     */
    @Test
    public void canHavePersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this.wordOfBundle("characters.en_US.words", 2)
                .persistentGrammemes().size(),
            CoreMatchers.equalTo(1)
        );
    }

    /**
     * Picks {@code index}'th form of a lexeme from a resource with lexemes'
     * markup.
     * @param resourceName Name of a resource with lexemes' markup
     * @param index Index of a lexeme in the markup
     * @return {@code index}'th lexeme from a resourse with lexemes' markup
     */
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
