package mase.tus.dss.labs.tcpip.sockets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class R2CopyFile {
  public static void main(String[] args) throws Exception {
    
    FileInputStream fin = new FileInputStream("test.txt");
    Scanner scanner = new Scanner(fin);
    
    FileOutputStream fout = new FileOutputStream("test2.txt") ;
    PrintWriter p = new PrintWriter(fout) ;

    while (scanner.hasNextLine()) {
      p.println(scanner.nextLine());
    }
    p.close();
  }
}