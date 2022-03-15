package lectures.week10.files.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathGeneral {

	public static void main(String[] args) {
		// absolute addresses begin with the root (c: on Windows and / on Unix)
		Path p1 = Paths.get("c:\\temp\\test.txt");
		Path p2 = Paths.get("c:", "temp", "test.txt");	// same a sp1, no escape chars needed

		// if you don't begin with root, it's a relative path, which means
		// Java looks from teh current directory
		Path p3 = Paths.get("temp", "test.txt");
	}

}
