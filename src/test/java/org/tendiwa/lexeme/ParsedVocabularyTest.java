package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.Russian;

/**
 * @since 0.1
 */
public final class ParsedVocabularyTest {
    @Test
    public void findsWords() throws Exception {
        final ParsedVocabulary bundle =
            new ParsedVocabulary(
                new Russian().grammar(),
                Collections.singletonList(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            "\"dragon\" [Муж] {",
                            "   дракон  [И Ед]",
                            "   драконы [И Мн]",
                            "}",
                            "\"bee\" [Жен] {",
                            "   пчела  [И Ед]",
                            "   пчёлы  [И Мн]",
                            "}"
                        )
                    )
                )
            );
        MatcherAssert.assertThat(
            bundle.lexemes().size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle.lexemes().containsKey("dragon"),
            CoreMatchers.equalTo(true)
        );
        MatcherAssert.assertThat(
            bundle.lexemes().get("dragon").baseForm(),
            CoreMatchers.equalTo("дракон")
        );
        MatcherAssert.assertThat(
            bundle.lexemes().get("dragon").form(
                new BasicGrammaticalMeaning(
                    Russian.Grammemes.Мн
                )
            ),
            CoreMatchers.equalTo("драконы")
        );
    }
}
