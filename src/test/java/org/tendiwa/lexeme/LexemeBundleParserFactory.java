package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.lexeme.antlr.LexemeBundleLexer;
import org.tendiwa.lexeme.antlr.LexemeBundleParser;

import java.io.IOException;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class LexemeBundleParserFactory {
    public LexemeBundleParserFactory() {

    }

    /**
     * Create from plain text.
     * @param wordsText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final LexemeBundleParser create(String... wordsText) {
        return this.createInMode(LexemeBundleLexer.DEFAULT_MODE, wordsText);
    }

    /**
     * Create from plain text with lexer initialized in a particular mode.
     * @param mode Mode argumentName.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final LexemeBundleParser createInMode(int mode, String... corpusText) {
        try {
            final LexemeBundleLexer lexer = new LexemeBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            corpusText
                        )
                    )
                )
            );
            lexer.mode(mode);
            return new LexemeBundleParser(
                new CommonTokenStream(
                    lexer
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
