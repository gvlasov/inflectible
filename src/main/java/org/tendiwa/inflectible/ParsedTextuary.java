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
 * A bundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextuary extends ForwardingMap<String, TextTemplate> {
    /**
     * Input stream with templates' markup.
     */
    private final List<InputStream> inputs;

    /**
     * Grammar of the language of templates.
     */
    private final Grammar grammar;

    /**
     * Resulting texts.
     */
    private final Map<String, TextTemplate> texts;

    /**
     * @param inputs Sources to parse into {@link TextTemplate}s.
     */
    ParsedTextuary(Grammar grammar, List<InputStream> inputs) {
        this.inputs = inputs;
        this.grammar = grammar;
        this.texts = this.parseTemplates();
    }

    /**
     * Parse templates.
     * @return Templates.
     */
    private Map<String, TextTemplate> parseTemplates() {
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

    /**
     * Obtains an identifier of a template.
     * @param parseTree ANTLR parse tree of a template.
     * @return Identifier of a template.
     */
    private String templateId(TemplateBundleParser.TemplateContext parseTree) {
        return parseTree.id().getText();
    }

    /**
     * Creates an ANTLR parser for a stream.
     * @param stream Input stream with templates' markup
     * @return ANTLR parser for {@code stream}
     */
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
