package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tenidwa.collections.utils.Collectors;

public final class InputStreamWordBundleTest {
    @Test
    public void findsWords() {
        final Stream<LexemeMarkup> words = new InputStreamWordBundle(
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
            .words();
        MatcherAssert.assertThat(
            words.collect(Collectors.toImmutableList()).size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            words.collect(Collectors.toImmutableList()).get(0).conceptionId(),
            CoreMatchers.equalTo("dragon")
        );
    }
}
