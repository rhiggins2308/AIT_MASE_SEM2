package lectures.week8.localisation;

import java.util.Locale;

public class CreatingLocales {

	public static void main(String[] args) {
		usingConstructors();
		usingBuiltInConstants();
		usingLocaleBuilder();
	}
	
	private static void usingConstructors() {
		System.out.println(new Locale("en"));					// en
		Locale locEnglishGB = new Locale("en", "GB");			// en_GB
		System.out.println(locEnglishGB.getDisplayLanguage()); 	// English
		System.out.println(locEnglishGB.getDisplayCountry()); 	// United Kingdom		
	}
	
	private static void usingBuiltInConstants() {
		System.out.println(Locale.FRENCH); 							// fr
		Locale locFrenchFrance = Locale.FRANCE;						// fr_FR
		System.out.println(locFrenchFrance.getDisplayLanguage()); 	// English
		System.out.println(locFrenchFrance.getDisplayCountry()); 	// United Kingdom		
	}
	
	private static void usingLocaleBuilder() {
		Locale locArabicEgypt = new Locale.Builder()
				.setLanguage("ar")				// language first
				.setRegion("EG")				// country second
				.build();	
		System.out.println(locArabicEgypt);		// ar_EG
		
		Locale locArabicKuwait = new Locale.Builder()
				.setRegion("KW")				// country first
				.setLanguage("ar")				// language second
				.build();
		System.out.println(locArabicKuwait); 	// ar_KW
	}
}