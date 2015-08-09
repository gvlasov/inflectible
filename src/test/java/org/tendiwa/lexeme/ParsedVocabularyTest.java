package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.English;

/**
 * @since 0.1
 */
public final class ParsedVocabularyTest {
    @Test
    public void findsWords() throws Exception {
        final ParsedVocabulary bundle =
            new ParsedVocabulary(
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
        MatcherAssert.assertThat(
            bundle.size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle.containsKey("dragon"),
            CoreMatchers.equalTo(true)
        );
        MatcherAssert.assertThat(
            bundle.get("dragon").baseForm(),
            CoreMatchers.equalTo("dragon")
        );
        MatcherAssert.assertThat(
            bundle.get("dragon").form(
                ImmutableSet.of(English.Grammemes.Plur)
            ),
            CoreMatchers.equalTo("dragons")
        );
    }
}
