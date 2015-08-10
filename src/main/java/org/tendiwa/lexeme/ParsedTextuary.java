package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * TextBundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class ParsedTextuary {

    private final List<InputStream> inputs;
    private final Grammar grammar;
    private final List<TextTemplate> texts;

    /**
     * @param inputs Sources to parse into {@link TextTemplate}s.
     */
    ParsedTextuary(Grammar grammar, List<InputStream> inputs) {
        this.inputs = inputs;
        this.grammar = grammar;
        this.texts = this.computeTexts();
    }

    public List<TextTemplate> texts() {
        return this.texts;
    }

    private List<TextTemplate> computeTexts() {
        return this.inputs.stream()
            .map(this::createParser)
            .flatMap(parser -> parser.text_bundle().text().stream())
            .map(BasicTextTemplate::new)
            .collect(Collectors.toImmutableList());
    }

    private TextBundleParser createParser(InputStream stream) {
        try {
            return
                new TextBundleParser(
                    new CommonTokenStream(
                        new TextBundleLexer(
                            new ANTLRInputStream(stream)
                        )
                    )
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
