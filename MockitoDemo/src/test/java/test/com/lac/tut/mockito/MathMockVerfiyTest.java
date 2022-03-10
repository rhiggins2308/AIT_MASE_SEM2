package test.com.lac.tut.mockito;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.lac.tut.mockito.Math;


public class MathMockVerfiyTest {
	Math mathObj;
	
	@Before
	public void create(){
		mathObj= mock(Math.class);
		when(mathObj.add(1,2)).thenReturn(3); 
	}
	
	@Test
	public void test() {
		//call the add function with 1,2 as arguments
		assertSame(mathObj.add(1,2),3);
		
		//Verify whether add method is tested with arguments 1,2 
		
		
		//Verify whether add method is called only once
		
	}

}
