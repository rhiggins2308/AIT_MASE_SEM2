package mockitoSpyStub;

import static org.mockito.Matchers.argThat;

import java.util.List;

import org.mockito.ArgumentMatcher;

public class ListContainsMatcher<T> extends ArgumentMatcher<List<T>> {

	private T element;
	
	public ListContainsMatcher(T element) {
		this.element = element;
	}
	
	@Override
	public boolean matches(Object argument) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) argument;
		return list.contains(element);
	}
	
	public static <T> List<T> contains(T element) {
		return argThat(new ListContainsMatcher<>(element));
	}
}
