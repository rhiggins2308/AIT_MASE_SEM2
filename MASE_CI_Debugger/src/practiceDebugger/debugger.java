package practiceDebugger;

public class debugger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Attempt to stepping into");
		//F5
		stepInto();
		
		System.out.println("Attempt to stepping Over");
		//F6
		stepOver();
		
		//Put breakpoint in console statemebnt fo stepOverMethod
		//F8
		System.out.println("Jump 1");
		System.out.println("Jump 2");
		jumpTo();
	}
	
	public static void stepInto() {
		
		System.out.println("stepping into");
	}

	public static void stepOver() {
		
		System.out.println("stepping over");
		
	}
	
	public static void jumpTo() {
		
		System.out.println("JumpTo");
		
	}

}
