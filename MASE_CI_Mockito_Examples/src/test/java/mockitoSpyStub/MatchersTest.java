package mockitoSpyStub;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchersTest {
	
	public interface TestForMock {
		
		public boolean usesPrimitives(int i, float f, double d, byte b, boolean bool);
		
		public boolean usesObjects(String s, Object o, Integer i);
		
		public boolean usesCollections(List<String> list, Map<Integer, String> map, Set<Object> set);
		
		public boolean usesString(String s);
		
		public boolean usesVarargs(String... s);
		
		public boolean usesObject(Object o);
		
	}
	
	@Mock
	TestForMock test;

	@Test
	public void test() {
		
		// default behaviour is to return false
		assertFalse(test.usesString("Hello"));
		
		when(test.usesObjects(any(), any(), any())).thenReturn(true);
		assertTrue(test.usesObjects("Hello", new Thread(), 17));
		Mockito.reset(test);
		
		when(test.usesObjects(anyString(), anyObject(), anyInt())).thenReturn(true);
		assertTrue(test.usesObjects("Hi there", new Float(18), 42));
		Mockito.reset(test);
		
		when(test.usesPrimitives(anyInt(), anyFloat(), anyDouble(), anyByte(), anyBoolean())).thenReturn(true);
		assertTrue(test.usesPrimitives(1, 43.4f, 3.141592654d, (byte)2, false));
		Mockito.reset(test);
		
		// Gives unchecked type conversion warning 
		when(test.usesCollections(anyList(), anyMap(), anySet())).thenReturn(true);
		assertTrue(test.usesCollections(Arrays.asList("Hello", "World"), Collections.EMPTY_MAP, Collections.EMPTY_SET));
		Mockito.reset(test);
		
		// Gives no warning
		when(test.usesCollections(anyListOf(String.class), anyMapOf(Integer.class, String.class), anySetOf(Object.class))).thenReturn(true);
		assertTrue(test.usesCollections(Collections.emptyList(), Collections.emptyMap(), Collections.emptySet()));
		Mockito.reset(test);
		
		// eq() must match exactly
		when(test.usesObjects(eq("Hello World"), any(Object.class),anyInt())).thenReturn(true);
		assertFalse(test.usesObjects("Hi World", new Object(), 360));
		assertTrue(test.usesObjects("Hello World", new Object(), 360));
		Mockito.reset(test);
		
		when(test.usesString(startsWith("Hello"))).thenReturn(true);
		assertTrue(test.usesString("Hello there"));
		Mockito.reset(test);
		
		when(test.usesString(endsWith("something"))).thenReturn(true);
		assertTrue(test.usesString("isn't that something"));
		Mockito.reset(test);
		
		when(test.usesString(contains("second"))).thenReturn(true);
		assertTrue(test.usesString("first, second, third."));
		Mockito.reset(test);
		
		// Regular Expression
		when(test.usesString(matches("^\\w+$"))).thenReturn(true);
		assertTrue(test.usesString("Weak_Password1"));
		assertFalse(test.usesString("@Str0nG!pa$$woR>%42"));
		Mockito.reset(test);
		
		when(test.usesString((String)isNull())).thenReturn(true);
		assertTrue(test.usesString(null));
		Mockito.reset(test);
		
		when(test.usesString((String)isNotNull())).thenReturn(true);
		assertTrue(test.usesString("Anything"));
		Mockito.reset(test);
		
		// Object Reference
		String string1 = new String("hello");
		String string2 = new String("hello");
		when(test.usesString(same(string1))).thenReturn(true);
		assertTrue(test.usesString(string1));
		assertFalse(test.usesString(string2));
		Mockito.reset(test);
		
		// Compare to eq()
		when(test.usesString(eq(string1))).thenReturn(true);
		assertTrue(test.usesString(string1));
		assertTrue(test.usesString(string2));
		Mockito.reset(test);
		
		when(test.usesVarargs(anyVararg())).thenReturn(true);
		assertTrue(test.usesVarargs("A","B","C","D","E"));
		assertTrue(test.usesVarargs("ABC", "123"));
		assertTrue(test.usesVarargs("Hello!"));
		Mockito.reset(test);
		
		when(test.usesObject(isA(String.class))).thenReturn(true);
		assertTrue(test.usesObject("A String Object"));
		assertFalse(test.usesObject(new Integer(7)));
		Mockito.reset(test);
		
		// Field equality using reflection
		when(test.usesObject(refEq(new SomeBeanWithoutEquals("abc", 123)))).thenReturn(true);
		assertTrue(test.usesObject(new SomeBeanWithoutEquals("abc", 123)));
		Mockito.reset(test);
		
		// Compare to eq()
		when(test.usesObject(eq(new SomeBeanWithoutEquals("abc", 123)))).thenReturn(true);
		assertFalse(test.usesObject(new SomeBeanWithoutEquals("abc", 123)));
		Mockito.reset(test);
		
		when(test.usesObject(eq(new SomeBeanWithEquals("abc", 123)))).thenReturn(true);
		assertTrue(test.usesObject(new SomeBeanWithEquals("abc", 123)));
		Mockito.reset(test);
	}
	
	public class SomeBeanWithoutEquals {
		private String string;
		private int number;
		
		public SomeBeanWithoutEquals(String string, int number) {
			this.string = string;
			this.number = number;
		}
	}
	
	public class SomeBeanWithEquals {
		private String string;
		private int number;
		
		public SomeBeanWithEquals(String string, int number) {
			this.string = string;
			this.number = number;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + number;
			result = prime * result
					+ ((string == null) ? 0 : string.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SomeBeanWithEquals other = (SomeBeanWithEquals) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (number != other.number)
				return false;
			if (string == null) {
				if (other.string != null)
					return false;
			} else if (!string.equals(other.string))
				return false;
			return true;
		}

		private MatchersTest getOuterType() {
			return MatchersTest.this;
		}
	}

}
