package org.tendiwa.lexeme;

import java.util.List;

/**
 * A particular form of a lexeme.
 *
 * <p>For example, <i>index</i> and <i>indices</i> are two different forms of
 * the lexeme <i>index</i>.
 */
class BasicWordForm implements WordForm {
	private final String string;
	private final List<Grammeme> grammemes;

	BasicWordForm(String string, List<Grammeme> grammemes) {
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
