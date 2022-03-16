package lectures.week10.files.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInfoMethods {

	public static void main(String[] args) {
		Path path = Paths.get("C:/home/java/workspace");
		fileName(path);
		pathName(path);
		getNumElements(path);
		pathParent(path);
		pathParent(path);
		pathSubpath(path);
		pathToString(path);
	}

	private static void fileName(Path path) {
		// Returns the filename, 
		// or the last element of the sequence of name elements
		System.out.println("getFileName: " + path.getFileName());
	}

	private static void pathName(Path path) {
		// Returns the path element corresponding to the specified index.
		// The 0th element is the one closest to the root.
		// (On Windows, the root is usually C:\ and on UNIX, the root is /)"
		System.out.println("getName(1): " + path.getName(1));
	}

	private static void getNumElements(Path path) {
		// Returns the number of elements in this path,
		// excluding the root
		System.out.println("getNameCount: " + path.getNameCount());
	}

	private static void pathParent(Path path) {
		// Returns the parent path, 
		// or null if this path does not have a parent
		System.out.println("getParent: " + path.getParent());
	}

	private static void pathRoot(Path path) {
		// Returns the root of this path, 
		// or null if this path does not have a root
		System.out.println("getRoot: " + path.getRoot());
	}

	private static void pathSubpath(Path path) {
		// Returns a subequence of this path (not including a root element)
		// as specified by the beginning (inclusive)
		// and ending (exclusive) indexes
		System.out.println("subpath(0, 2): " + path.subpath(0, 2));
	}

	private static void pathToString(Path path) {
		// Returns the string representation of this path
		System.out.println("toString: " + path.toString());
	}
}