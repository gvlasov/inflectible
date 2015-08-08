package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class WordBundleParserFactory {
    public WordBundleParserFactory() {

    }

    /**
     * Create from plain text.
     * @param wordsText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final WordBundleParser create(String... wordsText) {
        return this.createInMode(WordBundleLexer.DEFAULT_MODE, wordsText);
    }

    /**
     * Create from plain text with lexer initialized in a particular mode.
     * @param mode Mode argumentName.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final WordBundleParser createInMode(int mode, String... corpusText) {
        try {
            final WordBundleLexer lexer = new WordBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            corpusText
                        )
                    )
                )
            );
            lexer.mode(mode);
            return new WordBundleParser(
                new CommonTokenStream(
                    lexer
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
