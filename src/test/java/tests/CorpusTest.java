package tests;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import org.tendiwa.lexeme.Language;
import org.tendiwa.lexeme.implementations.Russian;

public class CorpusTest {

    private Language russian;

    @Before
    public void setup() {
        russian = new Russian();
    }

    @Test
    public void test() {
        russian.loadCorpus(Resources.getResource("messages.ru_RU.texts"));
        russian.loadDictionary(Resources.getResource("actions.ru_RU.words"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongCorpusLanguage() {
        russian.loadCorpus(Resources.getResource("messages.en_US.texts"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongDictionaryLanguage() {
        russian.loadDictionary(Resources.getResource("actions.en_US.words"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void corpusInsteadOfDictionary() {
        russian.loadDictionary(Resources.getResource("messages.ru_RU.texts"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dictionaryInsteadOfCorpus() {
        russian.loadCorpus(Resources.getResource("actions.ru_RU.words"));
    }
}
