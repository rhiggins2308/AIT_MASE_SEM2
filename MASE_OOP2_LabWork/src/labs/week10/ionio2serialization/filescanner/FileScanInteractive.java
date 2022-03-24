package labs.week10.ionio2serialization.filescanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileScanInteractive {

    // Count the number of times a particular string appears in a file
    public int countTokens(String file, String search) throws IOException {
        int instanceCount = 0;
        // Chain a FileReader to a BufferedReader to a Scanner
        try (FileReader fr = new FileReader(file);// FileReader - for reading character files
                // BufferedReader - Reads text from a character-input stream, buffering 
                // characters so as to provide for the efficient reading of characters, 
                // arrays, and lines.
                BufferedReader br = new BufferedReader(fr);
                // Scanner - A simple text scanner which can parse primitive types and 
                // strings using regular expressions.
                Scanner s = new Scanner(br)) {  // Scanner(Readable src) constructor
                                                // BufferedReader implements Readable
            s.useDelimiter("\\W");
            while (s.hasNext()) {   // Returns true if this scanner has another token in its input.
                if (search.equalsIgnoreCase(s.next().trim())) {
                    instanceCount++;
                }
            }
        } // try-with-resources will close the FileReader, BufferedReader and Scanner connections
        return instanceCount;
    }

    // Main method
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ScanFileInteractive <file to search>");
            System.exit(-1);
        }
        // Save the file name as a string
        // Its a String, hence "ObamaInaguralAddress.txt" as opposed to "./ObamaInaguralAddress.txt"
        String file = args[0];

        // Create an instance of the ScanFileInteractive class
        FileScanInteractive scan = new FileScanInteractive();

        // Wrap the System.in InputStream with a BufferedReader to read each line from the keyboard.
        // InputStream - superclass of all classes representing an input stream of bytes.
        // InputStreamReader - An InputStreamReader is a bridge from byte streams to character 
        // streams: It reads bytes and decodes them into characters using a specified charset.
        // BufferedReader - For top efficiency, consider wrapping an InputStreamReader 
        // within a BufferedReader.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String search = "";
            System.out.println("Searching through the file: " + file);
            while (true) {
                System.out.print("Enter the search string or q to exit: ");
                // readLine() - Reads a line of text. A line is considered to be terminated 
                // by any one of a line feed ('\n') or a carriage return ('\r')
                search = in.readLine().trim();
                if (search.equalsIgnoreCase("q")) {
                    break;
                }
                int count = scan.countTokens(file, search);
                System.out.println("The word \"" + search + "\" appears "
                        + count + " times in the file.");
            }
        } catch (IOException e) { // Catch any IO exceptions.
            System.out.println("Exception: " + e);
            System.exit(-1);
        }
    }
}