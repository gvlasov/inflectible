package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.WordBundleLexer;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * WordBundle that parses an InputStream to get words.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class InputStreamWordBundle implements WordBundle {
    private final ImmutableList<WordBundleEntry> words;

    InputStreamWordBundle(
        Language language,
        InputStream in
    ) {
        this.words = this.parseWords(language, in);
    }

    private ImmutableList<WordBundleEntry> parseWords(Language language, InputStream in) {
        try {
            return
                new WordBundleParser(
                    new CommonTokenStream(
                        new WordBundleLexer(
                            new ANTLRInputStream(
                                in
                            )
                        )
                    )
                )
                    .word_bundle()
                    .word()
                    .stream()
                    .map(word -> new BasicWordBundleEntry(language, word))
                    .collect(Collectors.toImmutableList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImmutableList<WordBundleEntry> words() {
        return this.words;
    }
}
