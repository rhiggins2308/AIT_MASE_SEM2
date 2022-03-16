package lectures.week10.files.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatingDirectories {

	public static void main(String[] args) {
		File myDir = new File("mydir");		// create an object
		myDir.mkdir();						// create an actual directory
		
		File myFile = new File(myDir, "myFile.txt");
		try {
			myFile.createNewFile();
		} catch (IOException e) { e.printStackTrace(); }
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(myFile);
			pw.println("new stuff");
			pw.flush(); 	// only needed when writing out
			pw.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
}