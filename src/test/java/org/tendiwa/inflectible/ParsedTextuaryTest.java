package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public final class ParsedTextuaryTest {
    @Test
    public void loadsTexts() throws Exception {
        Assert.assertEquals(
            3,
            new ParsedTextuary(
                Mockito.mock(Grammar.class),
                ImmutableList.of(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            "argumentName(args) {",
                            "  text [args]",
                            "}",
                            "id2(args2, more) {",
                            "  things [more] [args2][;more]",
                            "}",
                            "id3.compund(hey, you) {",
                            " [hey] [you]",
                            "}"
                        )
                    )
                )
            )
                .size()
        );
    }

    @Test(expected = UncheckedIOException.class)
    public void failsWithBadInput() throws Exception {
        InputStream badInput = Mockito.mock(InputStream.class);
        Mockito.when(badInput.read()).thenThrow(IOException.class);
        new ParsedTextuary(
            Mockito.mock(Grammar.class),
            ImmutableList.of(badInput)
        );
    }
}
