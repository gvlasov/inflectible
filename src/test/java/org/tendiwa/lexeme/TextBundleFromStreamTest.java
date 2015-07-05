package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mockito;

public final class TextBundleFromStreamTest {

    @Test
    public void loadsTexts() throws Exception {
        Assert.assertEquals(
            3,
            new TextBundleFromStream(
                Mockito.mock(Language.class),
                Mockito.mock(NativeSpeaker.class),
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "id(args) {",
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
                .texts()
                .size()
        );
    }

}
