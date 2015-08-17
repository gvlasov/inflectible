/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Georgy Vlasov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tendiwa.inflectible;

import com.google.common.collect.ForwardingMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * A bundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextuary extends ForwardingMap<String, Template> {
    /**
     * Input stream with templates' markup.
     */
    private final transient List<InputStream> inputs;

    /**
     * Grammar of the language of templates.
     */
    private final transient Grammar grammar;

    /**
     * Resulting texts.
     */
    private final transient Map<String, Template> texts;

    /**
     * Ctor.
     * @param grammemes Grammaf of the language of input templates
     * @param input InputStreams with markup of templates
     */
    ParsedTextuary(final Grammar grammemes, final List<InputStream> input) {
        super();
        this.inputs = input;
        this.grammar = grammemes;
        this.texts = this.parseTemplates();
    }

    // @checkstyle ProtectedMethodInFinalClassCheck (3 lines)
    @Override
    protected Map<String, Template> delegate() {
        return this.texts;
    }

    /**
     * Parse templates.
     * @return Templates.
     */
    private Map<String, Template> parseTemplates() {
        return this.inputs.stream()
            .map(stream -> this.createParser(stream))
            .flatMap(parser -> parser.templates().template().stream())
            .collect(
                Collectors.toMap(
                    (ctx) -> this.templateId(ctx),
                    templateCtx
                        -> new ParsedTemplate(this.grammar, templateCtx)
                )
            );
    }

    /**
     * Obtains an identifier of a template.
     * @param ctx ANTLR parse tree of a template.
     * @return Identifier of a template.
     */
    private String templateId(final TemplateBundleParser.TemplateContext ctx) {
        return ctx.id().getText();
    }

    /**
     * Creates an ANTLR parser for a stream.
     * @param stream Input stream with templates' markup
     * @return ANTLR parser for {@code stream}
     */
    private TemplateBundleParser createParser(final InputStream stream) {
        final TemplateBundleParser parser;
        try {
            parser = new TemplateBundleParser(
                new CommonTokenStream(
                    new TemplateBundleLexer(
                        new ANTLRInputStream(stream)
                    )
                )
            );
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
        return parser;
    }
}
