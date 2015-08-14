package org.tendiwa.lexeme;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.lexeme.antlr.TemplateBundleParser;
import org.tendiwa.lexeme.implementations.English;

import java.io.IOException;
import java.util.Map;

/**
 * @since 0.1
 */
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
            new ParsedTextTemplate(
                grammar,
                this.textContext(
                    Joiner.on('\n').join(
                        "texts.text(a,b) {",
                        "  Here come a [a] and two [b][Plur;a]. [A] is tall.",
                        "}"
                    )
                )
            ).fillUp(
                ImmutableList.of(
                    vocabulary.get("HUMAN"),
                    vocabulary.get("BEAR")
                ),
                ImmutableMap.of()
            ),
            CoreMatchers.equalTo(
                "Here come a human and two bears. Human is tall."
            )
        );
    }

    /**
     * Creates an ANTLR parse tree for a single text template.
     * @param template
     * @return
     * @throws IOException
     */
    private TemplateBundleParser.TextTemplateContext textContext(String template)
        throws IOException {
        return
            new BasicTemplateBundleParser(IOUtils.toInputStream(template))
                .textTemplates()
                .textTemplate(0);
    }
}
