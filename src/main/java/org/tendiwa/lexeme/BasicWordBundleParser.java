package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicWordBundleParser extends WordBundleParser {
    public BasicWordBundleParser(InputStream input) {
        super(
            new CommonTokenStream(
                new WordBundleLexer(
                    BasicWordBundleParser.createAntlrInputStream(input)
                )
            )
        );
    }

    private static ANTLRInputStream createAntlrInputStream(InputStream input) {
        try {
            return new ANTLRInputStream(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
