package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import com.google.common.collect.ForwardingList;
import junit.framework.Assert;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tendiwa.inflectible.antlr.TemplateBundleParserBaseListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GeorgyVlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public class TemplateBundleParserTest {
    /**
     * Parser can locate entry arguments in the markup.
     */
    @Test
    public void findsArguments() throws Exception {
        Assert.assertEquals(
            Arrays.asList("attacker", "action", "aim"),
            new Arguments(
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
            )
        );
    }

    /**
     * Parser can handle more than one entry per document.
     */
    @Test
    public void parsesMultipleEntriesInOneTemplateBundle() throws Exception {
        Assert.assertEquals(
            2,
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
            ).templates().template().size()
        );
    }

    private class Arguments extends ForwardingList<String> {
        private final List<String> arguments = new ArrayList<>();

        Arguments(TemplateBundleParser parser) {
            new ParseTreeWalker()
                .walk(
                    new TemplateBundleParserBaseListener() {
                        @Override
                        public void enterDeclaredArguments(
                            TemplateBundleParser.DeclaredArgumentsContext ctx
                        ) {
                            super.enterDeclaredArguments(ctx);
                            for (ParseTree child : ctx.ID()) {
                                arguments.add(child.getText());
                            }
                        }
                    },
                    parser.templates()
                );
        }

        @Override
        protected List<String> delegate() {
            return arguments;
        }
    }
}
