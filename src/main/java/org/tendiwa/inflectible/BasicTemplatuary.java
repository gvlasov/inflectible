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

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * {@link Templatuary} defined by a map from template identifiers to templates.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicTemplatuary implements Templatuary {
    /**
     * Map from template identifiers to templates.
     */
    private final transient ImmutableMap<TemplateName, Template> templates;

    /**
     * Ctor.
     * @param map Map from template identifiers to templates.
     */
    public BasicTemplatuary(final ImmutableMap<TemplateName, Template> map) {
        this.templates = map;
    }

    @Override
    public Template template(final TemplateName name) throws Exception {
        final Template template = this.templates.get(name);
        if (template == null) {
            throw new MissingTemplateException(name);
        }
        return template;
    }

    @Override
    public boolean hasTemplate(final TemplateName name) throws Exception {
        return this.stringMap().containsKey(name.string());
    }

    // To be refactored in #47
    /**
     * Cretes map from string template names to their templates.
     * @return Map from string template names to their templates
     * @throws Exception If could not create the map
     */
    private Map<String, Template> stringMap() throws Exception {
        return this.templates
            .keySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Rethrowing.rethrowFunction(TemplateName::string),
                    this.templates::get
                )
            );
    }
}
