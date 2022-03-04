package test.com.lac.tut.mockito;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lac.tut.mockito.Math;

public class MathTest {

	Math mathObj;
	@Before
	public void create(){
		mathObj= new Math();
	}
	
	@Test
	public void testAdd() {
		
		assertSame(3, mathObj.add(1,2));
	}

}
