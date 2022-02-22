package lectures.week5.streams;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class OptionalsNull {

	public static void main(String[] args) {
		
		// Object nulls
		Optional<String> optRH = howToDealWithNull("RH");
		optRH.ifPresent(System.out::println);
		
		Optional<String> optNull = howToDealWithNull(null);
		System.out.println(
				optNull.orElseGet(
						() -> "Empty optional"));
		
		
		// Primitive nulls
		OptionalDouble optAvg = IntStream.rangeClosed(1, 10).average();
		
		optAvg.ifPresent((d) -> System.out.println(d));
		System.out.println(optAvg.getAsDouble());
		System.out.println(optAvg.orElseGet(() -> Double.NaN));

	}

	private static Optional<String> howToDealWithNull(String param) {
		//Optional optReturn = param == null ? Optional.empty() : Optional.of(param);
		Optional optReturn = Optional.ofNullable(param);
		return optReturn;
	}

}
