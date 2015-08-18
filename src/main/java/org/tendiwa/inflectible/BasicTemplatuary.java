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

/**
 * {@link Templatuary} defined by a map from template identifiers to templates.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicTemplatuary implements Templatuary {
    /**
     * Map from template identifiers to templates.
     */
    private final transient ImmutableMap<String, Template> templates;

    /**
     * Ctor.
     * @param map Map from template identifiers to templates.
     */
    public BasicTemplatuary(final ImmutableMap<String, Template> map) {
        this.templates = map;
    }

    @Override
    public Template template(final String name) throws Exception {
        final Template template = this.templates.get(name);
        if (template == null) {
            throw new MissingTemplateException(name);
        }
        return template;
    }

    @Override
    public boolean hasTemplate(final String identifier) {
        return this.templates.containsKey(identifier);
    }
}
