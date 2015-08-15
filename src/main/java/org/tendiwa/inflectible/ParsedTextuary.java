package org.tendiwa.inflectible;

import com.google.common.collect.ForwardingMap;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TextBundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextuary extends ForwardingMap<String, TextTemplate> {

    private final List<InputStream> inputs;
    private final Grammar grammar;
    private final Map<String, TextTemplate> texts;

    /**
     * @param inputs Sources to parse into {@link TextTemplate}s.
     */
    ParsedTextuary(Grammar grammar, List<InputStream> inputs) {
        this.inputs = inputs;
        this.grammar = grammar;
        this.texts = this.computeTexts();
    }

    private Map<String, TextTemplate> computeTexts() {
        return this.inputs.stream()
            .map(this::createParser)
            .flatMap(parser -> parser.templates().template().stream())
            .collect(
                Collectors.toMap(
                    this::templateId,
                    templateCtx ->
                        new ParsedTextTemplate(this.grammar, templateCtx)
                )
            );
    }

    private String templateId(TemplateBundleParser.TemplateContext textCtx) {
        return textCtx.id().getText();
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
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected Map<String, TextTemplate> delegate() {
        return this.texts;
    }
}
