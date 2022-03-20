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
    
    @Before
    public void setupMock() {       
        foo = mock(Foo.class);
        when(foo.greet()).thenReturn(HELLO_WORLD);
    }
     
    @Test
    public void fooGreets() {
        System.out.println("Foo greets: " + foo.greet());
        assertEquals(HELLO_WORLD, foo.greet());
    }
     
    @Test
    public void barGreets() {
        Bar bar = new Bar();
        assertEquals(HELLO_WORLD, bar.greet(foo));
    }    
}