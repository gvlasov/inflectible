package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

import java.io.IOException;

/**
 * Creates TemplateBundleParsers.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class TemplateBundleParserFactory {
    public TemplateBundleParserFactory() {

    }

    /**
     * Create from plain text.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final TemplateBundleParser create(String... corpusText) {
        return this.createInMode(TemplateBundleLexer.DEFAULT_MODE, corpusText);
    }

    /**
     * Create from plain text with lexer initialized in a particular mode.
     * @param mode Mode argumentName.
     * @param corpusText Lines of corpus text. Will be joined with \n to
     * produce the actual text.
     * @return Parsed corpus.
     */
    public final TemplateBundleParser createInMode(int mode, String... corpusText) {
        try {
            final TemplateBundleLexer lexer = new TemplateBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            corpusText
                        )
                    )
                )
            );
            lexer.mode(mode);
            return new TemplateBundleParser(
                new CommonTokenStream(
                    lexer
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
