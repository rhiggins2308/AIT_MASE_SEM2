package test.com.lac.tut.mockito;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.lac.tut.mockito.Math;

/**
 * Test the add method of Math object
 */
public class MathMockAddTest {
	Math mathObj; //The object that needs to mocked
	
	@Before
	/**
	 * Create mock object before you use them
	 */
	public void create(){
		//Create Math mock Object
		mathObj= mock(Math.class);
		
		// Configure it to return 3 when arguments passed are 1,2
		when(mathObj.add(1, 2)).thenReturn(3);
	}
	
	@Test
	public void test() {
		 //Assert that math object return 3
		assertSame(mathObj.add(1, 2), 3);
	}

}
