package mockitoSpyStub;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static mockitoSpyStub.ListContainsMatcher.*;

@RunWith(MockitoJUnitRunner.class)
public class ListContainsMatcherTest {
	
	public interface TestClass {
		public boolean usesStrings(List<String> list);		
		public boolean usesIntegers(List<Integer> list);
	}
	
	private List<String> stringList = Arrays.asList("Hello", "Java", "Code", "Geek");
	private List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
	
	@Mock
	TestClass test;
	
	@Test
	public void test() throws Exception {
		when(test.usesStrings(contains("Java"))).thenReturn(true);
		when(test.usesIntegers(contains(5))).thenReturn(true);
		assertTrue(test.usesIntegers(integerList));
		assertTrue(test.usesStrings(stringList));
		Mockito.reset(test);

		when(test.usesStrings(contains("Something Else"))).thenReturn(true);
		when(test.usesIntegers(contains(42))).thenReturn(true);
		assertFalse(test.usesStrings(stringList));
		assertFalse(test.usesIntegers(integerList));
		Mockito.reset(test);
	}
}
