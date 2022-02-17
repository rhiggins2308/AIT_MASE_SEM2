package mase.tus.dss.labs.tcpip.sockets;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ReadFileServerApp {
  public static void main(String args[]) throws Exception {
    Socket s;
    ServerSocket ss = new ServerSocket(2001);
    while (true) {
      System.out.println("Waiting for connection");
      s = ss.accept();

      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);
      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);

      String fileName;
      fileName = r.nextLine();
      System.out.println("File requested " + fileName);

      FileInputStream fin = new FileInputStream(fileName);
      Scanner f = new Scanner(fin);

      while (f.hasNextLine()) {
        p.println(f.nextLine());
      }
      p.close();
    }

  }
}