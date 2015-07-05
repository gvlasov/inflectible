package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Georgy Vlasov (suseika@tenidwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class TextuaryTest {
    @Test
    public void storesTexts() {
        Assert.assertEquals(
            "hello",
            new InputStreamTextuary(
                Mockito.mock(Language.class),
                Mockito.mock(NativeSpeaker.class),
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "hey(you) {",
                        "  how are you",
                        "}",
                        "hello() {",
                        "  Greetings!",
                        "}"
                    )
                )
            ).getText("hello").id()
        );
    }
}
