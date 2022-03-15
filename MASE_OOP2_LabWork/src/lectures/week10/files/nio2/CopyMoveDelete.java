package lectures.week10.files.nio2;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyMoveDelete {

	public static void main(String[] args) {
		example1();
		example2();		
	}

	private static void example1() {
		Path source = Paths.get("/temp.test1.txt");		// exists
		Path target = Paths.get("/temp.test2.txt");		// doesn't exist yet
		
		try {
			Files.copy(source, target);					// now two copies of the file
			Files.delete(target);						// back to one copy
			Files.move(source, target);					// still one copy
		} catch (IOException e) { e.printStackTrace(); }
	}

	private static void example2() {
		Path one = Paths.get("/temp.test1.txt");		// exists
		Path two = Paths.get("/temp.test2.txt");		// exists
		Path targ = Paths.get("/temp.test3.txt");		// doesn't exist yet
		
		try {
			Files.copy(one, targ);						// now two copies of test1.txt
			Files.copy(two, targ);						// throws exception to avoid overwriting existing file
		} catch (FileAlreadyExistsException faee) {
			try {
				Files.copy(two, targ, StandardCopyOption.REPLACE_EXISTING);	// confirm that file is to be overwritten
			} catch (IOException e) { e.printStackTrace(); }
		} catch (IOException e) { e.printStackTrace(); }
	}

}
