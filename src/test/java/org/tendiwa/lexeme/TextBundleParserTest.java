package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ForwardingList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * @author GeorgyVlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public class TextBundleParserTest {
    /**
     * Parser can locate entry arguments in the markup.
     */
    @Test
    public void findsArguments() throws Exception {
        Assert.assertEquals(
            Arrays.asList("attacker", "action", "aim"),
            new Arguments(
                new TextBundleParser(
                    new CommonTokenStream(
                        new TextBundleLexer(
                            new ANTLRInputStream(
                                TextBundleLexerTest.class.getResourceAsStream(
                                    "messages.ru_RU.texts"
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    /**
     * Parser can handle more than one entry per document.
     */
    @Test
    public void parsesMultipleEntriesInOneTextBundle() throws Exception {
        Assert.assertEquals(
            2,
            new TextBundleParser(
                new CommonTokenStream(
                    new TextBundleLexer(
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
            ).text_bundle().text().size()
        );
    }

    private class Arguments extends ForwardingList<String> {
        private final List<String> arguments = new ArrayList<>();

        Arguments(TextBundleParser parser) {
            new ParseTreeWalker()
                .walk(
                    new TextBundleParserBaseListener() {
                        @Override
                        public void enterArguments(@NotNull TextBundleParser.ArgumentsContext ctx) {
                            super.enterArguments(ctx);
                            for (ParseTree child : ctx.ID()) {
                                arguments.add(child.getText());
                            }
                        }
                    },
                    parser.text_bundle()
                );
        }

        @Override
        protected List<String> delegate() {
            return arguments;
        }
    }

}
