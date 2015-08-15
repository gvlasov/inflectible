package org.tendiwa.inflectible;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tenidwa.collections.utils.Collectors;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
            .flatMap(parser -> parser.textTemplates().textTemplate().stream())
            .map(textCtx -> new ParsedTextTemplate(this.grammar, textCtx))
            .collect(Collectors.toImmutableList());
    }

    private TemplateBundleParser createParser(InputStream stream) {
        try {
            return
                new TemplateBundleParser(
                    new CommonTokenStream(
                        new TemplateBundleLexer(
                            new ANTLRInputStream(stream)
                        )
                    )
                );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
