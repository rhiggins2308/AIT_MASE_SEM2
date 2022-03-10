package labs.week8.localisation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class DateApplication {

    PrintWriter pw = new PrintWriter(System.out, true);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Locale ruLocale = new Locale("ru", "RU");
    Locale irlLocale = new Locale("en", "IE");   
    Locale currentLocale = irlLocale;           // start out with en_IE (i.e. English in Ireland) menu
    ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

    public static void main(String[] args) {
        DateApplication dateApp = new DateApplication();
        dateApp.java8();
        dateApp.run();
    }
    
    public void java8(){
    	// a.	What days of the week did World War II start and end?
    	LocalDate startWW2 = LocalDate.of(1939, Month.SEPTEMBER, 1);
    	LocalDate endWW2   = LocalDate.of(1945, Month.SEPTEMBER, 2);
    	System.out.println("WW II started on: "+startWW2.getDayOfWeek());
    	System.out.println("WW II ended on: "+endWW2.getDayOfWeek());
    	
    	/* b.	You need to be 18 years of age to consume alcohol. 
    	 * 		Given the birth dates of Joe Bloggs (12/2/2000) and 
    	 * 		Ann Bloggs (20/12/2010), figure out if they can be served? 
    	 * 		Output the answer for both.
    	 */
    	LocalDate now = LocalDate.now();
        LocalDate eighteenYearsAgo = now.minus(18, ChronoUnit.YEARS);
        
        LocalDate joeBloggsBirthday = LocalDate.of(2000, Month.FEBRUARY, 12);
        LocalDate annBloggsBirthday = LocalDate.of(2010, Month.DECEMBER, 20);
        
        System.out.println("To be served, you must be born before : "+eighteenYearsAgo);
        System.out.println("Joe Bloggs DOB is "+joeBloggsBirthday);
        
        if (joeBloggsBirthday.isBefore(eighteenYearsAgo)) {
            System.out.println("Yes. Joe is old enough to be served alcohol.");
        } else {
            System.out.println("No. Joe is too young to be served alcohol.");
        }
        System.out.println("Ann Bloggs DOB is "+annBloggsBirthday);
        
        if (annBloggsBirthday.isBefore(eighteenYearsAgo)) {
            System.out.println("Yes. Ann is old enough to be served alcohol.");
        } else {
            System.out.println("No. Ann is too young to be served alcohol.");
        }
        
        /* c.	I get up at 06:30 every day except Sunday. 
         * 		Declare a LocalTime that represents the Sunday time 
         * 		i.e. do NOT declare it as 07:30 but declare it with respect to 06:30.
         */
        LocalTime sundayRise = LocalTime.of(06, 30).plus(1, ChronoUnit.HOURS);
        System.out.println("I get up on Sunday at: "+sundayRise);

        /* d.	What time is it in Tripoli ? 
         * 		Hint: Zone is "Africa/Tripoli".
         * 		
         */
        ZoneId zoneTripoli = ZoneId.of("Africa/Tripoli");
        LocalDateTime nowDT = LocalDateTime.now();
        // Let's get the local time in Tripoli
        ZonedDateTime athloneZoneTime = nowDT.atZone(ZoneId.of("Europe/Dublin"));
        ZonedDateTime tripoliZoneTime = athloneZoneTime.withZoneSameInstant(zoneTripoli);
        // In Athlone it is: 2019-03-20T20:39:22.876Z[Europe/Dublin]
        // In Tripoli it is: 2019-03-20T22:39:22.876+02:00[Africa/Tripoli]
        System.out.println("In Athlone it is: "+athloneZoneTime);
        System.out.println("In Tripoli it is: "+tripoliZoneTime);

        /* e.	Format the current ZoneDateTime according to the following DateTimeFormatter patterns:
         * 		"yyyy/MM/dd HH:mm:ss z" and "yyyy/MM/dd"
         */
        String dateTimeFormatPattern = "yyyy/MM/dd HH:mm:ss z";
        String dateTimeFormatPattern2 = "yyyy/MM/dd";
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(dateTimeFormatPattern);
        DateTimeFormatter formatter2  = DateTimeFormatter.ofPattern(dateTimeFormatPattern2);
        ZonedDateTime nowZone = ZonedDateTime.now();
        System.out.println(
                nowZone + " formatted with DateTimeFormatter and '"
              + dateTimeFormatPattern + "': " + formatter.format(nowZone));
        System.out.println(
                nowZone + " formatted with DateTimeFormatter and '"
              + dateTimeFormatPattern2 + "': " + formatter2.format(nowZone));        
    }

    public void run() {
        String line = "";

        while (!(line.equals("q"))) {
            this.printMenu();
            try {
                line = this.br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (line) {
                case "1": this.setEnglish(); break;
                case "2": this.setFrench(); break;
                case "3": this.setChinese(); break;
                case "4": this.setRussian(); break;
            }
        }
    }

    public void printMenu() {
        pw.println("\n--- Choose Language Option ---");
        pw.println("1. " + messages.getString("menu1"));
        pw.println("2. " + messages.getString("menu2"));
        pw.println("3. " + messages.getString("menu3"));
        pw.println("4. " + messages.getString("menu4"));
        pw.println("q. " + messages.getString("menuq"));
        System.out.print(messages.getString("menucommand") + " ");
    }

    public void setEnglish() {
    	currentLocale = irlLocale;
    	messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public void setFrench() {
    	currentLocale = Locale.FRANCE;
    	messages = ResourceBundle.getBundle("labs.week8.localisation.MessagesBundle", currentLocale);
    }

    public void setChinese() {
    	currentLocale = Locale.SIMPLIFIED_CHINESE;
    	messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public void setRussian() {
    	currentLocale = ruLocale;
    	this.messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }
}