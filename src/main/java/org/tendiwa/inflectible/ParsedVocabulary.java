package org.tendiwa.inflectible;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * A vocabulary of lexemes that identifies each lexeme by a unique string.
 * Lexeme identifiers are uppercase words, e.g. RUN or TREE.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedVocabulary extends ForwardingMap<String, Lexeme> {
    /**
     * Input streams with lexemes' markup.
     */
    private final List<InputStream> input;

    /**
     * Grammar of the language of the lexemes.
     */
    private final Grammar grammar;

    /**
     * Found lexemes.
     */
    // TODO: To be refactored in #47
    private final ImmutableMap<String, Lexeme> lexemes;

    /**
     * Ctor.
     * @param grammar Grammar of the language of the lexemes
     * @param input Input streams with lexemes' markup
     */
    public ParsedVocabulary(Grammar grammar, List<InputStream> input) {
        this.input = input;
        this.grammar = grammar;
        this.lexemes = this.constructLexemes();
    }

    /**
     * Constructs lexemes from markup.
     * @return Lexemes constructed from the markup in the input stream
     */
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

    /**
     * Uppercase identifier of a lexeme, e.g. RUN or TREE.
     * @param parseTree ANTLR parse tree of a lexeme.
     * @return Identifier of a lexeme.
     */
    private String conceptionId(LexemeBundleParser.LexemeContext parseTree) {
        return parseTree.LEXEME_ID().getText();
    }

    @Override
    protected Map<String, Lexeme> delegate() {
        return this.lexemes;
    }
}
