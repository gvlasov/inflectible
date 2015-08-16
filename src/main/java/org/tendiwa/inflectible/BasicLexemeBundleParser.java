package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.inflectible.antlr.LexemeBundleLexer;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * A convenience descendant of {@link LexemeBundleParser} created from an
 * {@link InputStream} without explicitly specifying any additional plumbing.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexemeBundleParser extends LexemeBundleParser {
    /**
     * Ctor.
     * @param input Input stream with lexemes' markup
     */
    public BasicLexemeBundleParser(InputStream input) {
        super(
            new CommonTokenStream(
                new LexemeBundleLexer(
                    BasicLexemeBundleParser.createAntlrInputStream(input)
                )
            )
        );
    }

    /**
     * Ctor.
     * @param markup Lexemes' markup
     */
    public BasicLexemeBundleParser(String... markup) {
        this(
            IOUtils.toInputStream(
                Joiner.on('\n').join(markup)
            )
        );
    }

    /**
     * Creates an ANTLR input stream.
     * @param input Input stream with lexemes' markup
     * @return An ANTLR input stream with lexemes' characters
     */
    private static ANTLRInputStream createAntlrInputStream(InputStream input) {
        try {
            return new ANTLRInputStream(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
