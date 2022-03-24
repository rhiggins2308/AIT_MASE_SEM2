package mockitobehaviour;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static mockitobehaviour.Foo.*;

public class MockitoHelloWorld {
	 
		// Declare Foo reference for mocking
	    private Foo foo;    
	    
	    /* carry out mock and set up before every test
	     * mock the Foo.class object
	     * set up foo.greet() to return value of HELLO_WORLD in Foo class
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
	    
	    /* set up foo.greet() to return null when called
	     * when bar.question called
	     * 		invokes verifyFooConnection
	     *	 		which tries to call foo.greet()
	     * 			since foo is null, foo.greet() cannot be called
	     * 			so the if statement throws FooNotAvailable
	     * 
	     * the test expects FooNotAvailable to be thrown
	     */
	    @Test(expected=FooNotAvailable.class)
	    public void fooNotAvailable() {
	        Bar bar = new Bar();
	        System.out.println("Foo is down so will not respond");
	        when(foo.greet()).thenReturn(null);
	        System.out.println("Bar sends a question to Foo but since Foo is not avilable will throw FooNotAvailable");
	        bar.question(foo, "Hello Foo"); 
	    }
	    
	    /* when bar.question called
	     * with mocked foo
	     * and Foo.ANY_NEW_TOPICS string constant
	     * 		invokes verifyFooConnection
	     *	 		which is valid and satisfied the if statement
	     * 			so makes call to foo.question, which has no implementation
	     * 
	     * we just want to verify that 
	     * the call to foo.question() is reached and made once
	     * which it is, in this case
	     */
	    @Test
	    public void barQuestionsFoo() {
	        Bar bar = new Bar();
	        System.out.println("Bar asks Foo 'Any new topics?', it should get a response"); 
	        bar.question(foo, Foo.ANY_NEW_TOPICS);  
	        System.out.println("Verify that Foo has been asked the question");
	        verify(foo, times(1)).question(Foo.ANY_NEW_TOPICS);     
	    }
	    
	    /*
	     * Foo is available
	     * but string passed to bar.question() 
	     * does not equal ANY_NEW_TOPICS
	     * So just returns "Invalid Question"
	     * And never executes foo.question()
	     */
	    @Test
	    public void filterInvalidQuestions() {
	        Bar bar = new Bar();        
	        String invalidQuestion = "Invalid question";
	        bar.question(foo, invalidQuestion); 
	        System.out.println("Verify that question was never requested as Foo is un-available");
	        verify(foo, never()).question(invalidQuestion);
	    }
	}