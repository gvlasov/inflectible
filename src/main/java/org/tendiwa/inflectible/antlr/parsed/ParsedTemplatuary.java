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
package org.tendiwa.inflectible.antlr.parsed;

import com.google.common.collect.ImmutableMap;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.tendiwa.inflectible.BasicTemplatuary;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.Template;
import org.tendiwa.inflectible.TemplateName;
import org.tendiwa.inflectible.Templatuary;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * A bundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class ParsedTemplatuary implements Templatuary {
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
    private final transient Templatuary templatuary;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of input templates
     * @param input InputStreams with markup of templates
     * @throws Exception If couldn't parse templates
     */
    ParsedTemplatuary(
        final Grammar grammemes,
        final List<InputStream> input
    ) throws Exception {
        this.inputs = input;
        this.grammar = grammemes;
        this.templatuary = this.parseTemplates();
    }

    @Override
    public Template template(final TemplateName name) throws Exception {
        return this.templatuary.template(name);
    }

    @Override
    public boolean hasTemplate(final TemplateName identifier) {
        return this.templatuary.hasTemplate(identifier);
    }

    // To be refactored in #47
    /**
     * Parse templates.
     * @return Templatuary with templates.
     * @throws Exception If couldn't parse
     */
    private Templatuary parseTemplates() throws Exception {
        return new BasicTemplatuary(
            ImmutableMap.copyOf(
                this.inputs.stream()
                    .map(
                        Rethrowing.rethrowFunction(
                            BasicTemplateBundleParser::new
                        )
                    )
                    .flatMap(parser -> parser.templates().template().stream())
                    .collect(
                        Collectors.toMap(
                            TemplateName::new,
                            templateCtx
                                -> new ParsedTemplate(this.grammar, templateCtx)
                        )
                    )
            )
        );
    }
}
