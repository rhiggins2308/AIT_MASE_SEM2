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
        when(foo.greet()).thenReturn(HELLO_WORLD);
        System.out.println("Foo greets: " + foo.greet());
        assertEquals(foo.greet(), HELLO_WORLD);
    }
}