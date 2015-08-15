package org.tendiwa.inflectible;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.inflectible.antlr.LexemeBundleLexer;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexemeBundleParser extends LexemeBundleParser {
    public BasicLexemeBundleParser(InputStream input) {
        super(
            new CommonTokenStream(
                new LexemeBundleLexer(
                    BasicLexemeBundleParser.createAntlrInputStream(input)
                )
            )
        );
    }

    private static ANTLRInputStream createAntlrInputStream(InputStream input) {
        try {
            return new ANTLRInputStream(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
