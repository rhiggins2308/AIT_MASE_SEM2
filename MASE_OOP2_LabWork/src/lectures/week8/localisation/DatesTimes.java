package lectures.week8.localisation;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DatesTimes {

	public static void main(String[] args) {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		List<String> zoneList = new ArrayList<>(zoneIds);
		Collections.sort(zoneList);
		zoneList
			.stream()
			.forEach(System.out::println);
		
		ZonedDateTime utc = ZonedDateTime.of(2020, 4, 8, 11, 45, 0, 0, ZoneId.of("UCT"));
		System.out.println(utc);

		ZonedDateTime irishTime = utc.withZoneSameInstant(ZoneId.of("Europe/London"));
		System.out.println(irishTime);

		ZonedDateTime londonTime = utc.withZoneSameInstant(ZoneId.of("Eire"));
		System.out.println(londonTime);

		ZonedDateTime berlinTime = utc.withZoneSameInstant(ZoneId.of("Europe/Berlin"));
		System.out.println(berlinTime);
		
		LocalDateTime flightDepTime = LocalDateTime.of(2020,  Month.APRIL, 8, 16, 30);
		ZonedDateTime flightDepTimeZ = flightDepTime.atZone(ZoneId.of("Europe/Dublin"));
		System.out.println("Leaving at " + flightDepTimeZ);
		
		ZonedDateTime arrivalTimeInBerlin = 
				flightDepTimeZ
					.withZoneSameInstant(ZoneId.of("Europe/Berlin"))
					.plusHours(2)
					.plusMinutes(20);
		System.out.println("Arriviong at " + arrivalTimeInBerlin);
	}
}