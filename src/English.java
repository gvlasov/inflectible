public class English extends Language {
/**
 * @param localizedName
 * 	Name of the language in that language, for example "Русский" for Russian.
 */
protected English(String localizedName) {
	super(localizedName);
}
protected void stringToModifier(String modifier) {
	return English.Modifiers.valueOf(flag);
}

public enum Modifiers implements Modifier {
	Ger,
	Sing,
	Plur
}
}
