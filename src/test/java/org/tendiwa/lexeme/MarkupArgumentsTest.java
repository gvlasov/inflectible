package org.tendiwa.lexeme;

import java.util.Arrays;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class MarkupArgumentsTest {
    @Test
    public void mapsArgumentNamesToWords() {
        final Lexeme actor = Mockito.mock(Lexeme.class);
        final Lexeme seer = Mockito.mock(Lexeme.class);
        final Lexeme receiver = Mockito.mock(Lexeme.class);
        final MarkupArguments arguments = new MarkupArguments(
            new TextBundleParserFactory()
                .create(
                    "action.act(actor, seer, receiver) {",
                    "  this text",
                    "}"
                ).text(),
            Arrays.asList(actor, seer, receiver)
        );
        Assert.assertEquals(
            actor,
            arguments.getArgument("actor")
        );
        Assert.assertEquals(
            seer,
            arguments.getArgument("seer")
        );
        Assert.assertEquals(
            receiver,
            arguments.getArgument("receiver")
        );
        Assert.assertFalse(
            receiver.equals(arguments.getArgument("seer"))
        );
    }
}
