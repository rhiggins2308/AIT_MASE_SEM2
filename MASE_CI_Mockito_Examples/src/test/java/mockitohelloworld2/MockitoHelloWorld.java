package mockitohelloworld2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static mockitohelloworld2.Foo.*;

public class MockitoHelloWorld {
	private Foo foo;
    
	/* use @Before to mock the Foo interface, which has no method implementation
	 * and setup foo.greet() to return HELLO_WORLD
	 * from Foo class
	 * Before every test
	 */
    @Before
    public void setupMock() {       
        foo = mock(Foo.class);
        when(foo.greet()).thenReturn(HELLO_WORLD);
    }
    
    /* assert that the return value from foo.greet() 
     * equals the value of HELLO_WORLD
     */
    @Test
    public void fooGreets() {
        System.out.println("Foo greets: " + foo.greet());
        assertEquals(HELLO_WORLD, foo.greet());
    }
     
    /* create actual new Bar object (not mock)
     * assert that the call to bar.greet()
     * 		(which in turn takes a Foo object parameter
     * 		and calls foo.greet())
     * returns the value of HELLO_WORLD in the Foo class
     * 		as per the mocked behaviour
     */
    @Test
    public void barGreets() {
        Bar bar = new Bar();
        assertEquals(HELLO_WORLD, bar.greet(foo));
    }    
}