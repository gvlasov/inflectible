package org.tendiwa.lexeme;

import java.util.List;

/**
 * A particular form of a word.
 *
 * <p>For example, <i>index</i> and <i>indices</i> are two different forms of
 * the word <i>index</i>.
 */
class BasicLexeme implements Lexeme {
	private final String string;
	private final List<Grammeme> grammemes;

	BasicLexeme(String string, List<Grammeme> grammemes) {
		this.string = string;
		this.grammemes = grammemes;
	}

	@Override
    public List<Grammeme> getGrammemes() {
		return grammemes;
	}

	@Override
	public String toString() {
		return string;
	}
}
