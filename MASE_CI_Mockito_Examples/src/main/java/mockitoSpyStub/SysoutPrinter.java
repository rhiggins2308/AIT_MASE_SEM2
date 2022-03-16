package mockitoSpyStub;

public class SysoutPrinter implements Printer {

	@Override
	public void printTestPage() {
		System.out.println("This is a test page");
	}

}

