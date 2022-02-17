package mase.tus.dss.labs.tcpip.sockets;

import java.io.FileInputStream;
import java.util.Scanner;

public class R1ReadFile {
  public static void main(String[] args) throws Exception {
    
    FileInputStream fin = new FileInputStream("test.txt");
    Scanner scanner = new Scanner(fin);

    while (scanner.hasNextLine()) {
      System.out.println(scanner.nextLine());
    }
  }
}