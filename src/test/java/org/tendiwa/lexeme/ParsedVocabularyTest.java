package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.lexeme.implementations.English;

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
            bundle.containsKey("dragon"),
            CoreMatchers.equalTo(true)
        );
    }

    @Test
    public void findsWordForms() throws Exception {
        final ParsedVocabulary bundle = this.englishVocabulary();
        MatcherAssert.assertThat(
            bundle.get("dragon").baseForm(),
            CoreMatchers.equalTo("dragon")
        );
        Placeholder placeholder = Mockito.mock(FillablePlaceholder.class);
        Mockito.when(placeholder.grammaticalMeaning())
            .thenReturn(ImmutableSet.of(English.Grammemes.Plur));
        MatcherAssert.assertThat(
            bundle.get("dragon").formForPlaceholder(placeholder),
            CoreMatchers.equalTo("dragons")
        );
    }

    private ParsedVocabulary englishVocabulary() {
        return new ParsedVocabulary(
            new English().grammar(),
            Collections.singletonList(
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "\"dragon\" {",
                        "   dragon  [Sing]",
                        "   dragons [Plur]",
                        "}",
                        "\"bee\" {",
                        "   bee  [Sing]",
                        "   bees [Plur]",
                        "}"
                    )
                )
            )
        );
    }
}
