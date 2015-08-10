package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.implementations.English;

public final class ParsedTextTemplateTest {
    @Test
    public void fillsUpItself() throws Exception {
        final Grammar grammar = new English().grammar();
        final Map<String, Lexeme> vocabulary = new ParsedVocabulary(
            grammar,
            ImmutableList.of(
                ParsedTextTemplateTest.class.getResourceAsStream(
                    "characters.en_US.words"
                )
            )
        );
        MatcherAssert.assertThat(
            new ParsedTextTemplate(grammar, this.textContext()).fillUp(
                ImmutableList.of(
                    vocabulary.get("human"),
                    vocabulary.get("bear")
                )
            ),
            CoreMatchers.equalTo("Here come a human and two bears")
        );
    }

    private TextBundleParser.TextContext textContext() throws IOException {
        return
            new BasicTextBundleParser(
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "texts.text(a,b) {",
                        "  Here come a [a] and two [b][Plur]",
                        "}"
                    )
                )
            )
                .text_bundle()
                .text(0);
    }
}
