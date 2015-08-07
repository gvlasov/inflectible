package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public final class InputStreamWordBundleTest {
    @Test
    public void findsWords() {
        final WordBundle bundle =
            new InputStreamWordBundle(
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
            );
        MatcherAssert.assertThat(
            bundle
                .words()
                .size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle
                .words()
                .get(0)
                .conceptionId(),
            CoreMatchers.equalTo("dragon")
        );
    }
}
