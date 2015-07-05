package org.tendiwa.lexeme;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Textuary that loads its texts from InputStreams upon construction.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class InputStreamTextuary implements Textuary {
    private final Map<String, MarkedUpText> texts;

    /**
     *
     * @param language
     * @param vocabulary
     * @param corpusStreams InputStreams from which corpuses are loaded. Closed
     * automatically.
     */
    public InputStreamTextuary(
        Language language,
        Vocabulary vocabulary,
        InputStream... corpusStreams
    ) {
        this.texts = new HashMap<>(corpusStreams.length);
        for (InputStream corpusStream : corpusStreams) {
            try (InputStream stream = corpusStream) {
                final TextBundle textBundle = new TextBundleFromStream(
                    language,
                    vocabulary,
                    stream
                );
                for (MarkedUpText text : textBundle.texts()) {
                    this.addText(text);
                }
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addText(MarkedUpText text) {
        this.texts.put(text.id(), text);
    }

    @Override
    public MarkedUpText getText(String id) {
        return this.texts.get(id);
    }
}
