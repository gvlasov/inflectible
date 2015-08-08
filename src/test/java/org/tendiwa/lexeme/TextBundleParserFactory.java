package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * Creates TextBundleParsers.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class TextBundleParserFactory {
    public TextBundleParserFactory() {

    }

    /**
     * Create from plain text.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final TextBundleParser create(String... corpusText) {
        return this.createInMode(TextBundleLexer.DEFAULT_MODE, corpusText);
    }

    /**
     * Create from plain text with lexer initialized in a particular mode.
     * @param mode Mode argumentName.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final TextBundleParser createInMode(int mode, String... corpusText) {
        try {
            final TextBundleLexer lexer = new TextBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            corpusText
                        )
                    )
                )
            );
            lexer.mode(mode);
            return new TextBundleParser(
                new CommonTokenStream(
                    lexer
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
