package lectures.week8.localisation;

import java.util.Locale;

public class DefaultLocaleExample {

	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		System.out.println(locale); // en_IE
	}
}