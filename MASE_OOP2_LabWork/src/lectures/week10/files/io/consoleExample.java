package lectures.week10.files.io;

import java.io.Console;

public class consoleExample {

	public static void main(String[] args) {
		String name = "";
		Console console = System.console();	// not "new Console()"
		// readPassword() does not echo on the screen
		// %s is the format of teh arg "pwd: " i.e. String
		
		char[] pw = console.readPassword("%s", "pwd: ");
	}
}