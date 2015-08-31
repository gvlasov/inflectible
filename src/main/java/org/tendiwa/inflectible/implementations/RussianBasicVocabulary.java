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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.tendiwa.inflectible.BasicLexeme;
import org.tendiwa.inflectible.BasicVocabulary;
import org.tendiwa.inflectible.Concept;
import org.tendiwa.inflectible.GmEmpty;
import org.tendiwa.inflectible.GmValidated;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.SpBasic;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.Vocabulary;

/**
 * Vocabulary with basic Russian words: pronouns and more to come.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 * @checkstyle MultipleStringLiteralsCheck (800 lines)
 */
public final class RussianBasicVocabulary implements Vocabulary {
    /**
     * Basic lexemes of Russian language.
     */
    private static final ImmutableMap<Concept, Lexeme> LEXEMES =
        ImmutableMap.<Concept, Lexeme>builder()
            .put(
                () -> "МЕСТОИМЕНИЕ",
                new BasicLexeme(
                    new GmEmpty(),
                    ImmutableMap.<GrammaticalMeaning, Spelling>builder()
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("я")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("меня")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("мне")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("меня")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("мной")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("мне")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("ты")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("тебя")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("тебе")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("тебя")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("тобой")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("тебе")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("он")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("него")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("ему")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("его")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("ним")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Муж,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("нём")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("она")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("неё")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("ней")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("неё")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("ней")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Жен,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("ней")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("оно")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("него")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("ему")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("его")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("ним")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Средн,
                                    RussianGrammeme.Ед,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("нём")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("мы")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("нас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("нам")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("нас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("нами")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.I,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("нас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("вы")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("вас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("вам")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("вас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("вами")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.II,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("вас")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.И
                                )
                            ),
                            new SpBasic("они")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Р
                                )
                            ),
                            new SpBasic("них")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Д
                                )
                            ),
                            new SpBasic("ним")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.В
                                )
                            ),
                            new SpBasic("них")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.Т
                                )
                            ),
                            new SpBasic("ними")
                        )
                        .put(
                            new GmValidated(
                                RussianPartOfSpeech.Местоим,
                                ImmutableSet.of(
                                    RussianGrammeme.III,
                                    RussianGrammeme.Мн,
                                    RussianGrammeme.П
                                )
                            ),
                            new SpBasic("них")
                        )
                        .build()
                )
            )
            .build();

    @Override
    public Lexeme lexeme(final Concept concept) throws Exception {
        return this.delegate().lexeme(concept);
    }

    @Override
    public boolean hasLexeme(final Concept concept) throws Exception {
        return this.delegate().hasLexeme(concept);
    }

    /**
     * Creates basic delegate.
     * @return Basic delegate
     */
    private Vocabulary delegate() {
        return new BasicVocabulary(RussianBasicVocabulary.LEXEMES);
    }
}
