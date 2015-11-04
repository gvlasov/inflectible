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

import com.google.common.base.Joiner;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.LexemeParser;
import org.tendiwa.inflectible.antlr.TemplateParser;
import org.tendiwa.inflectible.implementations.English;
import org.tendiwa.inflectible.implementations.EnglishGrammeme;
import org.tendiwa.inflectible.implementations.Russian;
import org.tendiwa.inflectible.implementations.RussianGrammeme;

/**
 * Unit tests for {@link GrammemesMarkup}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class GrammemesMarkupTest {
    /**
     * {@link GrammemesMarkup} can extract grammemes from a parse tree of a
     * lexeme.
     * @throws IOException If fails
     */
    @Test
    public void extractsGrammemesFromLexemeParseTree() throws IOException {
        MatcherAssert.assertThat(
            new GrammemesMarkup(
                this.grammemesFromLexemeParser(
                    "HUMAN (Сущ) <Муж> {",
                    " человек",
                    " людей <Мн В>",
                    " ... ",
                    "}"
                )
            )
                .grammemes(new Russian().grammar()),
            Matchers.contains(RussianGrammeme.Мн, RussianGrammeme.В)
        );
    }

    /**
     * {@link GrammemesMarkup} can extract grammemes from a parse tree of a
     * template.
     * @throws IOException If fails
     */
    @Test
    public void extractsGrammemesFromTemplateParseTree() throws IOException {
        MatcherAssert.assertThat(
            new GrammemesMarkup(
                this.grammemesFromTemplateParser(
                    "text(dude) {",
                    "  Hello [dude]<Sing I>",
                    "} "
                )
            )
                .grammemes(new English().grammar()),
            Matchers.contains(EnglishGrammeme.Sing, EnglishGrammeme.I)
        );
    }

    /**
     * Returns grammemes context obtained from a lexeme parser.
     * @param markup Template markup
     * @return Grammemes context
     * @throws IOException If fails
     */
    private LexemeParser.GrammemesContext grammemesFromLexemeParser(
        final String... markup
    )
        throws IOException {
        return new BasicLexemeParser(
            IOUtils.toInputStream(
                Joiner.on('\n').join(markup)
            )
        )
            .lexeme()
            .wordForms()
            .inflectedWordForm(0)
            .grammaticalMeaning()
            .grammemes();
    }

    /**
     * Returns grammemes context obtained from a template parser.
     * @param markup Template markup
     * @return Grammemes context
     * @throws IOException If fails
     */
    private TemplateParser.GrammemesContext grammemesFromTemplateParser(
        final String... markup
    )
        throws IOException {
        return new BasicTemplateParser(
            IOUtils.toInputStream(
                Joiner.on('\n').join(markup)
            )
        )
            .template()
            .templateBody()
            .line(0)
            .piece(1)
            .twoPartPlaceholder()
            .grammaticalMeaning()
            .grammemes();
    }
}
