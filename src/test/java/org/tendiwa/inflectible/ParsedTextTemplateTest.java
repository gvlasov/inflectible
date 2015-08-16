package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tendiwa.inflectible.implementations.English;

import java.io.IOException;
import java.util.Map;

/**
 * Unit tests for {@link ParsedTextTemplate}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextTemplateTest {
    /**
     * ParsedTextTemplate can fill itself up with arguments.
     * @throws Exception If fails
     */
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
                this.templateContext(
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
     * @param template Template markup
     * @return ANTLR parse tree created from the markup
     * @throws IOException If fails
     */
    private TemplateBundleParser.TemplateContext templateContext(
        String template
    ) throws IOException {
        return
            new BasicTemplateBundleParser(IOUtils.toInputStream(template))
                .templates()
                .template(0);
    }
}
