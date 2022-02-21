package lectures.week4.lambdas;

interface I {
	void m();	// a functional interface i.e. it has only one
				// abstract method
}

public class BasicLambdas {

	public static void main(String[] args) {
		
		// Defining the implementation for functional interface method m()
		I lambdaI = () -> {
			System.out.println("Lambda version");
		};
		// when there is only a single code statement, then we do not require the curly braces,
		// to simplify the code even firther
		I lambdaI2 = () -> System.out.println("Lambda version");

		// calling the functional interface method, implemented above
		lambdaI.m();
		lambdaI2.m();
	}
}