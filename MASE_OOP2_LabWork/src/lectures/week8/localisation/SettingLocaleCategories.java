package lectures.week8.localisation;

import java.text.NumberFormat;
import java.util.Locale;

public class SettingLocaleCategories {

	public static void main(String[] args) {
		double n = 77_000.11;
		Locale locFrench = new Locale("fr", "FR");
		
//		Locale.setDefault(locFrench); 	// changes *both* DISPLAY and FORMAT
		
		Locale defaultLocale = Locale.getDefault();		// default locale is "en_IE" (English in Ireland)
		System.out.println(defaultLocale.getDisplayName()); 		// English (Ireland)
		System.out.println(defaultLocale.getDisplayLanguage()); 	// English
		System.out.println(defaultLocale.getDisplayCountry()); 		// Ireland
		System.out.println(NumberFormat.getInstance().format(n));	// 77,000.11 (en_IE locale)
		
		Locale.setDefault(Locale.Category.DISPLAY, locFrench);
		System.out.println("\nDISPLAY changed: " + defaultLocale.getDisplayName()); 		// anglais (Irlande)
		System.out.println("\nDISPLAY changed: " + defaultLocale.getDisplayLanguage()); 	// anglais
		System.out.println("\nDISPLAY changed: " + defaultLocale.getDisplayCountry()); 		// Irlande
		System.out.println("\nDISPLAY changed: " + NumberFormat.getInstance().format(n));	// 77,000.11 (en_IE locale)
		
		Locale.setDefault(Locale.Category.FORMAT, locFrench);
		System.out.println("\nFORMAT changed: " + NumberFormat.getInstance().format(n));	// 77 000,11
	}
}