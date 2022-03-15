package lectures.week10.files.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathExists {

	public static void main(String[] args) {
		// On Windows PC:
//		Path p1 = Paths.get("test\\aabc.txt");		// ok, "test" subdir of curr dir exists
//		Path p1 = Paths.get("test/aabc.txt");		// ok, "test" subdir of curr dir exists
//		Path p1 = Paths.get("/test/bc.txt");		// exception (c:\test does not exist)
		Path p1 = Paths.get("/Java/bc.txt");		// ok (c:\Java dir exists)
		
		try {
			Files.createFile(p1);
		} catch (IOException ex) {
			Logger.getLogger(PathExists.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}