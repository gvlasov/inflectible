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
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link BasicTemplatuary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicTemplatuaryTest {
    /**
     * {@link BasicTemplatuary} can return a template by its name if it is
     * present in the templatuary.
     * @throws Exception If fails
     */
    @Test
    public void returnsTemplateByIdentifier() throws Exception {
        final TemplateName identifier = new TnBasic("message");
        final Template template = Mockito.mock(Template.class);
        MatcherAssert.assertThat(
            new BasicTemplatuary(
                ImmutableMap.of(identifier, template)
            )
                .template(identifier),
            CoreMatchers.is(template)
        );
    }

    /**
     * {@link BasicTemplatuary} can fail if it doesn't have a template with the
     * specified name.
     * @throws Exception If Templatuary doesn't have a template with the
     *  specified name
     */
    @Test(expected = Exception.class)
    public void returnsEmptyIfNoSuchIdentifier() throws Exception {
        new BasicTemplatuary(ImmutableMap.of())
            .template(new TnBasic("occurrence"));
    }
}
