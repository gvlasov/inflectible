package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * TextBundle of texts loaded from a textual input stream.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class TextBundleFromStream implements TextBundle {

    private final Language language;
    private final NativeSpeaker nativeSpeaker;
    private final InputStream stream;

    /**
     * @param stream Source to parse into {@link MarkedUpText}s.
     */
    TextBundleFromStream(
        Language language,
        NativeSpeaker nativeSpeaker,
        InputStream stream
    ) {
        this.language = language;
        this.nativeSpeaker = nativeSpeaker;
        this.stream = stream;
    }

    @Override
    public final List<MarkedUpText> texts() {
        try {
            final List<TextBundleParser.TextContext> texts =
                new TextBundleParser(
                    new CommonTokenStream(
                        new TextBundleLexer(
                            new ANTLRInputStream(
                                this.stream
                            )
                        )
                    )
                ).text_bundle().text();
            final List<MarkedUpText> answer = new ArrayList<>(texts.size());
            for (TextBundleParser.TextContext ctx : texts) {
                answer.add(
                    new BasicMarkedUpText(
                        ctx
                    )
                );
            }
            return answer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
