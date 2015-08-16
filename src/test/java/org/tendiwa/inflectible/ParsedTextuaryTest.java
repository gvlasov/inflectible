package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * Unit tests for {@link ParsedTextuary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextuaryTest {
    /**
     * ParsedTextuary can load templates from an input stream.
     * @throws Exception
     */
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

    /**
     * ParsedTextuary can fail if it can't read from its input stream.
     * @throws Exception If fails
     */
    @Test(expected = UncheckedIOException.class)
    public void failsWithBadInput() throws Exception {
        InputStream badInput = Mockito.mock(InputStream.class);
        Mockito.when(badInput.read()).thenThrow(new IOException());
        new ParsedTextuary(
            Mockito.mock(Grammar.class),
            ImmutableList.of(badInput)
        );
    }
}
