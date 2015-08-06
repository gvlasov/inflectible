package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * WordBundle that parses an InputStream to get words.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class InputStreamWordBundle implements WordBundle {

    private final InputStream input;

    InputStreamWordBundle(InputStream input) {
        this.input = input;
    }

    @Override
    public Stream<LexemeMarkup> words() {
        try {
            return
                new WordBundleParser(
                    new CommonTokenStream(
                        new WordBundleLexer(
                            new ANTLRInputStream(this.input)
                        )
                    )
                )
                    .word_bundle()
                    .word()
                    .stream()
                    .map(ParsedLexemeMarkup::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
