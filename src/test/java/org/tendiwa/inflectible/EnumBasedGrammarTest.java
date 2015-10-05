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

import java.util.Set;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link EnumBasedGrammar}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class EnumBasedGrammarTest {
    /**
     * EnumBasedGrammar can return a grammeme by its name.
     * @throws Exception If fails
     */
    @Test
    public void findsGrammemeByName() throws Exception {
        MatcherAssert.assertThat(
            new EnumBasedGrammar(
                KobaianGrammemes.class,
                KobaianPartOfSpeech.class
            )
                .grammemeByName("Default"),
            CoreMatchers.equalTo(KobaianGrammemes.Default)
        );
    }

    /**
     * {@link EnumBasedGrammar} can return a part of speech by its name.
     * @throws Exception If fails
     */
    @Test
    public void findsPartOfSpeechByName() throws Exception {
        MatcherAssert.assertThat(
            new EnumBasedGrammar(
                KobaianGrammemes.class,
                KobaianPartOfSpeech.class
            )
                .partOfSpeechByName("Basic"),
            CoreMatchers.equalTo(KobaianPartOfSpeech.Basic)
        );
    }

    /**
     * EnumBasedGrammar can fail if a class that is not an enum is provided
     * to its constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void disallowsNonEnumGrammemes() {
        new EnumBasedGrammar(
            Grammeme.class,
            KobaianPartOfSpeech.class
        );
    }

    /**
     * Grammemes for Kobaian language.
     */
    private enum KobaianGrammemes implements Grammeme {
        /**
         * The only grammeme.
         */
        Default {
            @Override
            public GrammaticalCategory category() {
                return Mockito.mock(GrammaticalCategory.class);
            }
        }
    }

    /**
     * Parts of speech for Kobaian language.
     */
    private enum KobaianPartOfSpeech implements PartOfSpeech {
        /**
         * The only part of speech.
         */
        Basic {
            @Override
            public boolean usesCategory(final GrammaticalCategory category) {
                return false;
            }

            @Override
            public Set<GrammaticalMeaning> meaningVariations()
                throws Exception {
                throw new UnsupportedOperationException();
            }

            @Override
            public Lexeme lexeme(
                final Spelling headword,
                final GrammaticalMeaning persistent
            ) throws Exception {
                throw new UnsupportedOperationException();
            }
        };
    }
}
