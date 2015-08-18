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

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedTemplate}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTemplateTest {
    /**
     * ParsedTextTemplate can fill itself up with arguments.
     * @throws Exception If fails
     */
    @Test
    @SuppressWarnings("PMD.UseConcurrentHashMap")
    public void fillsUpItself() throws Exception {
        final Grammar grammar = new English().grammar();
        final Vocabulary vocabulary = new ParsedVocabulary(
            grammar,
            ImmutableList.of(
                ParsedTemplateTest.class.getResourceAsStream(
                    "characters.en_US.words"
                )
            )
        );
        MatcherAssert.assertThat(
            new ParsedTemplate(
                grammar,
                this.templateContext(
                    Joiner.on('\n').join(
                        "texts.text(a,b) {",
                        "  Here come a [a] and two [b][Plur;a]. [A] is tall.",
                        "}"
                    )
                )
            ).fillUp(
                ImmutableList.of(
                    vocabulary.lexeme("HUMAN"),
                    vocabulary.lexeme("BEAR")
                ),
                new BasicVocabulary()
            ),
            CoreMatchers.equalTo(
                "Here come a human and two bears. Human is tall."
            )
        );
    }

    /**
     * Creates an ANTLR parse tree for a single text template.
     * @param template Template markup
     * @return ANTLR parse tree created from the markup
     * @throws IOException If fails
     */
    private TemplateBundleParser.TemplateContext templateContext(
        final String template
    ) throws IOException {
        return
            new BasicTemplateBundleParser(IOUtils.toInputStream(template))
                .templates()
                .template(0);
    }
}
