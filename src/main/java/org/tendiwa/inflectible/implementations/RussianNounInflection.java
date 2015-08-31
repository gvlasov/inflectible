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

import com.google.common.collect.ImmutableSet;
import org.tendiwa.inflectible.GmValidated;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.inflection.PartOfSpeechInflection;
import org.tendiwa.inflectible.inflection.Stem;

/**
 * Inflection of Russian nouns.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class RussianNounInflection implements PartOfSpeechInflection {
    @Override
    public Stem createStem(
        final GrammaticalMeaning persistent,
        final Spelling headword
    ) {
        return new RussianNounStem(persistent, headword);
    }

    @Override
    public ImmutableSet<GrammaticalMeaning> allPossibleInflectedMeanings() {
        return ImmutableSet.of(
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.И
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Р
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Д
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.В
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Т
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.П
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.И
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Р
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Д
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.В
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Т
                )
            ),
            new GmValidated(
                RussianPartOfSpeech.Сущ,
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.П
                )
            )
        );
    }
}
