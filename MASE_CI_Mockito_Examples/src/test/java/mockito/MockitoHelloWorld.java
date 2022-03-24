package mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static mockito.Foo.*;

public class MockitoHelloWorld {
    @Test
    public void fooGreets() {
    	// foo object is mocked
        Foo foo = mock(Foo.class);
        
        // Set up foo.greet() to return the STring variable HELLO_WORLD
        when(foo.greet()).thenReturn(HELLO_WORLD);
        
        System.out.println("Foo greets: " + foo.greet());
        
        // Assert that the call to foo.greet() 
        // returns the String variable value HELLO_WORLD 
        assertEquals(foo.greet(), HELLO_WORLD);
    }
}