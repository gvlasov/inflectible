package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Unit tests for {@link TemplateBundleParser}.
 * @author GeorgyVlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class TemplateBundleParserTest {
    /**
     * TemplateBundleParser can locate entry arguments in the markup.
     */
    @Test
    public void findsArguments() throws Exception {
        MatcherAssert.assertThat(
            new TemplateBundleParser(
                new CommonTokenStream(
                    new TemplateBundleLexer(
                        new ANTLRInputStream(
                            TemplateBundleLexerTest.class.getResourceAsStream(
                                "messages.ru_RU.texts"
                            )
                        )
                    )
                )
            )
                .templates()
                .template(0)
                .declaredArguments()
                .ID(2)
                .getText(),
            CoreMatchers.equalTo("aim")
        );
    }

    /**
     * TemplateBundleParser can handle more than one template per bundle.
     */
    @Test
    public void parsesMultipleEntriesInOneTemplateBundle() throws Exception {
        MatcherAssert.assertThat(
            new TemplateBundleParser(
                new CommonTokenStream(
                    new TemplateBundleLexer(
                        new ANTLRInputStream(
                            IOUtils.toInputStream(
                                Joiner.on('\n').join(
                                    "hey.man(a) {",
                                    "  // Man",
                                    "  Hey, man [a]!",
                                    "}",
                                    "",
                                    "yo.dude(b) {",
                                    "  Yo, dude [b]!",
                                    "}"
                                )
                            )
                        )
                    )
                )
            )
                .templates()
                .template()
                .size(),
            CoreMatchers.equalTo(2)
        );
    }
}
