package lectures.week8.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * The heirarchy is decided upon the first filename (resource bundle) that exists when we are searching for resource bundles.
 * 1. Find a resource bundle (based on filename) - this locks the heirarchy in place.
 * 2. Look for the key in the heirarchy
 * 		- properties files and java/class files do not share a heirarchy
 */

public class BundleSearching {

	public static void main(String[] args) {
		defaultLocaleIrrelevant();
		ignoreAmericanBundle();
		usingDefaultLocale();
	}

	private static void defaultLocaleIrrelevant() {
		Locale.setDefault(new Locale("en", "IE"));
		Locale localeCA = new Locale("en", "CA");
		/* Search path:
		 * 	1a. Mill_en_CA.java
		 * 	1b. Mill_en_CA.properties (found this file - heirarchy is now decided)
		 * 	2.	Now we are locked into a specific heirarchy (this means that the 
		 * 		default locale Mill_en_IE.java/properties is irrelevant).
		 * 		The heirarchy searched is now properties files only:
		 * 		- Mill_en_CA.properties
		 * 		- Mill_en.properties
		 * 		- Mill.properties (default bundle)
		 */
		
		ResourceBundle rb = ResourceBundle.getBundle("lectures.week8.localisation.Mill", localeCA);
		/* Because we are locked into a heirarchy (see above), this is why "name" below
		 * comes up with "Some Mill" even though the "name" key is in the default locale
		 * as well i.e. Mill_en_IE.properties.
		 */
		System.out.println(rb.getString("name"));	// Some Mill
		System.out.println("-------------");
		
		
	}

	private static void ignoreAmericanBundle() {
		Locale.setDefault(new Locale("en", "IE"));
		Locale localeUS = new Locale("en", "US");
		/* Search path:
		 * 	1a. Mill_en_US.java
		 * 	1b. Mill_en_US.properties
		 * 	2a.	Mill_en.java (remove country)
		 * 	2b.	Mill_en.properties (found, lock the heirarchy)
		 * 	3.	Now we are locked into a specific heirarchy (this means that the 
		 * 		default locale Mill_en_IE.java/properties is irrelevant).
		 * 		The heirarchy searched is now properties files only:
		 * 		- Mill_en.properties
		 * 		- Mill.properties (default bundle)
		 */
		
		ResourceBundle rb = ResourceBundle.getBundle("lectures.week8.localisation.Mill", localeUS);
		System.out.println(rb.getString("open"));	// is open
		System.out.println(rb.getString("name"));	// Some Mill
		System.out.println("-------------");
	}

	private static void usingDefaultLocale() {
		Locale.setDefault(new Locale("en", "IE"));
		Locale localeFR = new Locale("fr", "FR");	// French in France (no such bundle)
		/* Search path:
		 * 	1a. Mill_fr_FR.java
		 * 	1b. Mill_fr_FR.properties
		 * 	2a.	Mill_fr.java (remove country)
		 * 	2b.	Mill_fr.properties 
		 * 	3a.	Mill_en_IE.java (default locale)
		 * 	3b.	Mill_en_IE.properties (found, lock the heirarchy)
		 * 	4.	Now we are locked into a specific heirarchy.
		 * 		- Mill_en_IE.properties (default locale).
		 * 		The heirarchy searched is now properties files only:
		 * 		- Mill_en.properties
		 * 		- Mill.properties (default bundle)
		 */
		
		ResourceBundle rb = ResourceBundle.getBundle("lectures.week8.localisation.Mill", localeFR);
		System.out.println(rb.getString("open"));	// is open
		System.out.println(rb.getString("name"));	// Irish Mill
		System.out.println("-------------");
	}
}
