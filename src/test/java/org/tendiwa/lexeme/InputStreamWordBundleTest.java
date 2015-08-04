package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.implementations.Russian;

public final class InputStreamWordBundleTest {
    @Test
    public void findsWords() {
        final ImmutableList<WordBundleEntry> words = new InputStreamWordBundle(
            new Russian().grammar(),
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
        Assert.assertEquals(
            2,
            words.size()
        );
        MatcherAssert.assertThat(
            words.asList().get(0).conceptionId(),
            CoreMatchers.equalTo("dragon")
        );
    }


}
