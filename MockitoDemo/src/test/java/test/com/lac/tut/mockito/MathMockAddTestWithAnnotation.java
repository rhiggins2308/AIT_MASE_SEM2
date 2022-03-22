package test.com.lac.tut.mockito;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.lac.tut.mockito.Math;

/**
* Test the add method of Math object with  Mockito annotation
*/
public class MathMockAddTestWithAnnotation {
	@Mock
	Math mathObj;
	
	@Before
	/**
	 * Create mock object before you use them
	 */
	public void create(){
		initMocks(this);// Initialize this mock objects
		when(mathObj.add(1, 2)).thenReturn(3); // Configure it to return 3 when arguments passed are 1,2
	}
	
	@Test
	public void test() {
		assertSame(3, mathObj.add(1,2));//Assert that math object return 3
	}
}