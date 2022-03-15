package lectures.week10.files.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatingFiles {

	public static void main(String[] args) {
		createWithFile();
		createWithPrintWriter();

	}

	private static void createWithFile() {
		File file = new File("foo");	// only creates a File object
										// DOES NOT create the actual file
		
		try {													// make a file, "foo" which
			file.createNewFile();								// is assigned to 'file'		
		} catch (IOException e) { e.printStackTrace(); }
		
	}

	private static void createWithPrintWriter() {
		File file = new File("foo");	// only creates a File object
										// DOES NOT create the actual file
		
		try {															// make a PrintWriter object AND
			PrintWriter pw = new PrintWriter(file);						// make a file, "foo" to which
		} catch (FileNotFoundException e) { e.printStackTrace(); }		// 'file' is assigned, AND assign
																		// 'pw' to PrintWriter
		
	}

}
