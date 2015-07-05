package org.tendiwa.lexeme;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

public class TextBundleLexerTest {
    @Test
    public void splitsIntoTokens() throws Exception {
        final String text = StringUtils.join(
            "log.get_damage(attacker, action, aim) {",
            "    // Медведь кусает человека, человек получает пизды",
            "    [Attacker] [action][III] [aim][Р], [aim] получает пизды",
            "}"
        );
        MatcherAssert.assertThat(
            new TextBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(text)
                )
            )
                .nextToken()
                .getText(),
            CoreMatchers.equalTo("log")
        );
    }
}
