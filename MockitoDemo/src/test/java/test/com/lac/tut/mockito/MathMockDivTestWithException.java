package test.com.lac.tut.mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.lac.tut.mockito.Math;

/**
 * Test division method of Math class
 */
public class MathMockDivTestWithException {
	Math mathObj;
	
	@Before
	public void create(){
		 //Create Math Object
		 // Configure it to return exception when denominator is zero
	}
	
	@Test(expected=ArithmeticException.class) //expect the method throws ArithmeticException
	public void test() {
		mathObj.div(1,0); //call the div and expect to return ArithmeticException
	}

}
