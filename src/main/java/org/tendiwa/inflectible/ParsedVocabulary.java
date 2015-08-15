package org.tendiwa.inflectible;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
    // TODO: To be refactored in #47
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
                .map(BasicLexemeBundleParser::new)
                .flatMap(parser -> parser.lexemes().lexeme().stream())
                .collect(
                    java.util.stream.Collectors.toMap(
                        this::conceptionId,
                        ctx -> new ParsedLexeme(this.grammar, ctx)
                    )
                )
        );
    }

    private String conceptionId(LexemeBundleParser.LexemeContext ctx) {
        return ctx.LEXEME_ID().getText();
    }

    @Override
    protected Map<String, Lexeme> delegate() {
        return this.lexemes;
    }
}
