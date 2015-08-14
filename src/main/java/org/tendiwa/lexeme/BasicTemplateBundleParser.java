package org.tendiwa.lexeme;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.TemplateBundleLexer;
import org.tendiwa.lexeme.antlr.TemplateBundleParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicTemplateBundleParser extends TemplateBundleParser {
    public BasicTemplateBundleParser(InputStream input) throws IOException {
        super(
            new CommonTokenStream(
                new TemplateBundleLexer(
                    new ANTLRInputStream(input)
                )
            )
        );
    }

}
