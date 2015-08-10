package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicTextBundleParser extends TextBundleParser {
    public BasicTextBundleParser(InputStream input) throws IOException {
        super(
            new CommonTokenStream(
                new TextBundleLexer(
                    new ANTLRInputStream(input)
                )
            )
        );
    }

}
