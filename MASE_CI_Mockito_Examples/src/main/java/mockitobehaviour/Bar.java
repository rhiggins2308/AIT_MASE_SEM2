package mockitobehaviour;

public class Bar {

	public String greet(Foo foo) {
        System.out.println("Bar invokes Foo.greet");
        return foo.greet();
    }
     
    public String question(Foo foo, String question) {
        verifyFooConnection(foo);
        if (Foo.ANY_NEW_TOPICS.equals(question)) {
            return foo.question(question);
        }
        return "Invalid Question";
    }
 
    public void verifyFooConnection(Foo foo) {
        System.out.println("Is Foo available?");
        String response = foo.greet();
        if (!Foo.HELLO_WORLD.equals(response)) {
            System.out.println("No");
            throw new FooNotAvailable();
        }
        System.out.println("Yes");
    }
	}