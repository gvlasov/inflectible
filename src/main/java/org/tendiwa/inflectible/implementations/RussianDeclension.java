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
package org.tendiwa.inflectible.implementations;
// @checkstyle AvoidStaticImportCheck (11 lines)

import com.google.common.collect.ImmutableMap;
import org.tendiwa.inflectible.GrammaticalMeaning;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.В;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.Д;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.Ед;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.И;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.Мн;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.П;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.Р;
import static org.tendiwa.inflectible.implementations.RussianGrammeme.Т;

/**
 * Inflection rules for Russian nouns.
 * <p/>
 * Inflection of nouns is called
 * <a href="https://en.wikipedia.org/wiki/Declension">declension</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 * @checkstyle MultipleStringLiteralsCheck (300 lines)
 */
@SuppressWarnings(
    {
        "PMD.TooManyStaticImports",
        "PMD.AvoidDuplicateLiterals"
    }
    )
public enum RussianDeclension implements Declension {
    /**
     * Words that inflect like мама, папа, голова, карта, сирота.
     */
    МамаПапа(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "a")
            .put(new GmOfRussianNoun(Ед, Р), "ы")
            .put(new GmOfRussianNoun(Ед, Д), "е")
            .put(new GmOfRussianNoun(Ед, В), "у")
            .put(new GmOfRussianNoun(Ед, Т), "ой")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "ы")
            .put(new GmOfRussianNoun(Мн, Р), "")
            .put(new GmOfRussianNoun(Мн, Д), "ам")
            .put(new GmOfRussianNoun(Мн, В), "")
            .put(new GmOfRussianNoun(Мн, Т), "ами")
            .put(new GmOfRussianNoun(Мн, П), "ах")
            .build()
    ),

    /**
     * Words inflecting like дядя, тётя, Витя, сопля, конопля, ладья (letters
     * Е and Ё are treated as the same letter in Витей/ладьёй, that's an
     * unsolvable problem).
     */
    ДядяТётя(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "я")
            .put(new GmOfRussianNoun(Ед, Р), "и")
            .put(new GmOfRussianNoun(Ед, Д), "е")
            .put(new GmOfRussianNoun(Ед, В), "ю")
            .put(new GmOfRussianNoun(Ед, Т), "ей")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "и")
            .put(new GmOfRussianNoun(Мн, Р), "ь")
            .put(new GmOfRussianNoun(Мн, Д), "ям")
            .put(new GmOfRussianNoun(Мн, В), "ь")
            .put(new GmOfRussianNoun(Мн, Т), "ями")
            .put(new GmOfRussianNoun(Мн, П), "ях")
            .build()
    ),

    /**
     * Words inflecting like окно, сукно, говно, бревно, полотно, вино, пиво.
     */
    Окно(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "о")
            .put(new GmOfRussianNoun(Ед, Р), "а")
            .put(new GmOfRussianNoun(Ед, Д), "у")
            .put(new GmOfRussianNoun(Ед, В), "")
            .put(new GmOfRussianNoun(Ед, Т), "ом")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "а")
            .put(new GmOfRussianNoun(Мн, Р), "")
            .put(new GmOfRussianNoun(Мн, Д), "ам")
            .put(new GmOfRussianNoun(Мн, В), "а")
            .put(new GmOfRussianNoun(Мн, Т), "ами")
            .put(new GmOfRussianNoun(Мн, П), "ах")
            .build()
    ),

    /**
     * Words inflecting like море, горе, варенье, печенье.
     */
    Море(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "е")
            .put(new GmOfRussianNoun(Ед, Р), "я")
            .put(new GmOfRussianNoun(Ед, Д), "ю")
            .put(new GmOfRussianNoun(Ед, В), "е")
            .put(new GmOfRussianNoun(Ед, Т), "ем")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "я")
            .put(new GmOfRussianNoun(Мн, Р), "ей")
            .put(new GmOfRussianNoun(Мн, Д), "ям")
            .put(new GmOfRussianNoun(Мн, В), "я")
            .put(new GmOfRussianNoun(Мн, Т), "ями")
            .put(new GmOfRussianNoun(Мн, П), "ях")
            .build()
    ),

    /**
     * Words that inflect like кот, медвед, султан, кирасир, штаны, джинсы.
     */
    Кот(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "")
            .put(new GmOfRussianNoun(Ед, Р), "а")
            .put(new GmOfRussianNoun(Ед, Д), "у")
            .put(new GmOfRussianNoun(Ед, В), "a")
            .put(new GmOfRussianNoun(Ед, Т), "ом")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "ы")
            .put(new GmOfRussianNoun(Мн, Р), "ов")
            .put(new GmOfRussianNoun(Мн, Д), "ам")
            .put(new GmOfRussianNoun(Мн, В), "ов")
            .put(new GmOfRussianNoun(Мн, Т), "ами")
            .put(new GmOfRussianNoun(Мн, П), "ах")
            .build()
    ),

    /**
     * Words that inflect like гусь, Русь, викарий, буй.
     */
    Гусь(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "ь")
            .put(new GmOfRussianNoun(Ед, Р), "я")
            .put(new GmOfRussianNoun(Ед, Д), "ю")
            .put(new GmOfRussianNoun(Ед, В), "я")
            .put(new GmOfRussianNoun(Ед, Т), "ем")
            .put(new GmOfRussianNoun(Ед, П), "е")
            .put(new GmOfRussianNoun(Мн, И), "и")
            .put(new GmOfRussianNoun(Мн, Р), "ей")
            .put(new GmOfRussianNoun(Мн, Д), "ям")
            .put(new GmOfRussianNoun(Мн, В), "ей")
            .put(new GmOfRussianNoun(Мн, Т), "ями")
            .put(new GmOfRussianNoun(Мн, П), "ях")
            .build()
    ),

    /**
     * Words that inflect like метель, хрень, дурь, стать, явь.
     */
    Метель(
        ImmutableMap.<GmOfRussianNoun, String>builder()
            .put(new GmOfRussianNoun(Ед, И), "ь")
            .put(new GmOfRussianNoun(Ед, Р), "и")
            .put(new GmOfRussianNoun(Ед, Д), "и")
            .put(new GmOfRussianNoun(Ед, В), "ь")
            .put(new GmOfRussianNoun(Ед, Т), "ью")
            .put(new GmOfRussianNoun(Ед, П), "и")
            .put(new GmOfRussianNoun(Мн, И), "и")
            .put(new GmOfRussianNoun(Мн, Р), "ей")
            .put(new GmOfRussianNoun(Мн, Д), "ям")
            .put(new GmOfRussianNoun(Мн, В), "и")
            .put(new GmOfRussianNoun(Мн, Т), "ями")
            .put(new GmOfRussianNoun(Мн, П), "ях")
            .build()
    ),

    /**
     * Non-inflected category of words like кофе, Душанбе, Гюльчатай. Words
     * usually go in this category if their headword has an ending that is
     * not common for their grammatical gender.
     */
    Кофе(ImmutableMap.<GmOfRussianNoun, String>of()) {
        @Override
        public String ending(
            final GrammaticalMeaning meaning
        ) throws Exception {
            return "";
        }
    };

    /**
     * Map from grammatical meanings to endings.
     */
    private final transient ImmutableMap<GmOfRussianNoun, String> endings;

    /**
     * Ctor.
     * @param ends Map from grammatical meanings to endings.
     */
    RussianDeclension(final ImmutableMap<GmOfRussianNoun, String> ends) {
        this.endings = ends;
    }

    @Override
    public String ending(final GrammaticalMeaning meaning) throws Exception {
        return this.endings.get(new GmOfRussianNoun(meaning));
    }
}
