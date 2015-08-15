package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mockito;

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
                .texts()
                .size()
        );
    }

}
