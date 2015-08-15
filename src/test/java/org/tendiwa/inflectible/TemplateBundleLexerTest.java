package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;

public class TemplateBundleLexerTest {
    @Test
    public void splitsIntoTokens() throws Exception {
        final String text = Joiner.on('\n').join(
            "log.get_damage(attacker, action, aim) {",
            "    // Медведь кусает человека, человек получает пизды",
            "    [Attacker] [action][III] [aim][Р], [aim] получает пизды",
            "}"
        );
        MatcherAssert.assertThat(
            new TemplateBundleLexer(
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
