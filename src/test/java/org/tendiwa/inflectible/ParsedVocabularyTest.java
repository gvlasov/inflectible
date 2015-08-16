package org.tendiwa.inflectible;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

import java.util.Collections;

/**
 * Unit tests for {@link ParsedVocabulary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedVocabularyTest {
    /**
     * ParsedVocabulary can parse multiple input streams to a
     * Map<String, Lexeme>.
     * @throws Exception If fails
     */
    @Test
    public void findsWords() throws Exception {
        final ParsedVocabulary bundle = this.englishVocabulary();
        MatcherAssert.assertThat(
            bundle.size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle.containsKey("DRAGON"),
            CoreMatchers.equalTo(true)
        );
    }


    /**
     * Creates a small vocabulary for {@link English} language.
     * @return Vocabulary for {@link English}
     */
    private ParsedVocabulary englishVocabulary() {
        return new ParsedVocabulary(
            new English().grammar(),
            Collections.singletonList(
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "DRAGON {",
                        "   dragon  [Sing]",
                        "   dragons [Plur]",
                        "}",
                        "BEE {",
                        "   bee  [Sing]",
                        "   bees [Plur]",
                        "}"
                    )
                )
            )
        );
    }
}
