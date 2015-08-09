package org.tendiwa.lexeme;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.tenidwa.collections.utils.Collectors;

/**
 * WordBundle that parses an InputStream to get words.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedVocabulary
    extends ForwardingMap<String, Lexeme> implements Map<String, Lexeme> {

    private final List<InputStream> input;
    private final Grammar grammar;
    private final ImmutableMap<String, Lexeme> lexemes;

    public ParsedVocabulary(Grammar grammar, List<InputStream> input) {
        this.input = input;
        this.grammar = grammar;
        this.lexemes = this.constructLexemes();
    }


    private ImmutableMap<String, Lexeme> constructLexemes() {
        return ImmutableMap.copyOf(
            this.input
                .stream()
                .map(BasicWordBundleParser::new)
                .flatMap(parser -> parser.word_bundle().word().stream())
                .map(ParsedLexemeMarkup::new)
                .collect(
                    java.util.stream.Collectors.toMap(
                        ParsedLexemeMarkup::conceptionId,
                        this::createLexeme
                    )
                )
        );
    }

    private BasicLexeme createLexeme(ParsedLexemeMarkup lexemeMarkup) {
        return new BasicLexeme(
            lexemeMarkup.persistentGrammemes()
                .stream()
                .map(this.grammar::grammemeByName)
                .collect(Collectors.toImmutableSet())
            ,
            lexemeMarkup.wordForms().stream()
                .map(
                    wordFormCtx ->
                        new WordFormFromMarkup(
                            wordFormCtx,
                            this.grammar
                        )
                )
                .collect(Collectors.toImmutableList())
        );
    }

    @Override
    protected Map<String, Lexeme> delegate() {
        return this.lexemes;
    }
}
