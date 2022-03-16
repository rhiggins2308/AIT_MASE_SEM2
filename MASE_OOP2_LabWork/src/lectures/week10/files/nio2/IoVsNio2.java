package lectures.week10.files.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoVsNio2 {

	public static void main(String[] args) {
		createEmptyFile();
		createEmptyDir();
		createDirWithMissingParents();
		checkIfExists();
	}

	private static void createEmptyFile() {
		// IO Approach
		File file = new File("test");
		try {
			file.createNewFile();
		} catch (IOException e) { e.printStackTrace(); }
		
		// NIO.2 Approach
		Path path = Paths.get("test");
		try {
			Files.createFile(path);
		} catch (IOException e) { e.printStackTrace(); }
		
	}

	private static void createEmptyDir() {
		// IO Approach
		File file = new File ("dir");
		file.mkdir();
		
		// NIO2 Approach
		Path path = Paths.get("dir");
		try {
			Files.createDirectory(path);
		} catch (IOException e) { e.printStackTrace(); }
	}

	private static void createDirWithMissingParents() {
		// IO Approach
		File file = new File("a/b/c");
		file.mkdirs();
		
		// NIO2 Approach
		Path path = Paths.get("a/b/c");
		try {
			Files.createDirectories(path);
		} catch (IOException e) { e.printStackTrace(); }
	}

	private static void checkIfExists() {
		// IO Approach
		File file = new File("test");
		file.exists();
		
		// NIO2 Approach
		Path path = Paths.get("test");
		Files.exists(path);
	}	
}