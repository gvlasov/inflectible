package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;

import java.util.Collections;

/**
 * @since 0.1
 */
public final class ParsedVocabularyTest {
    @Test
    public void findsWords() throws Exception {
        final ParsedVocabulary bundle = this.englishVocabulary();
        MatcherAssert.assertThat(
            bundle.size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle.containsKey("DRAGON"),
            CoreMatchers.equalTo(true)
        );
    }

    @Test
    public void findsWordForms() throws Exception {
        final ParsedVocabulary bundle = this.englishVocabulary();
        MatcherAssert.assertThat(
            bundle.get("DRAGON").defaultSpelling(),
            CoreMatchers.equalTo("dragon")
        );
        MatcherAssert.assertThat(
            bundle.get("DRAGON").wordForm(
                ImmutableSet.of(English.Grammemes.Plur)
            ),
            CoreMatchers.equalTo("dragons")
        );
    }

    private ParsedVocabulary englishVocabulary() {
        return new ParsedVocabulary(
            new English().grammar(),
            Collections.singletonList(
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "DRAGON {",
                        "   dragon  [Sing]",
                        "   dragons [Plur]",
                        "}",
                        "BEE {",
                        "   bee  [Sing]",
                        "   bees [Plur]",
                        "}"
                    )
                )
            )
        );
    }
}
