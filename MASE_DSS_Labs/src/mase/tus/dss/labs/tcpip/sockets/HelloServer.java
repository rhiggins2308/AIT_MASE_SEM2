package mase.tus.dss.labs.tcpip.sockets;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloServer {

  public static void main(String[] args) throws Exception {
    Socket s;
    ServerSocket ss = new ServerSocket(2000);
    
    while (true) {
      System.out.println("Server: waiting for connection ..");
      s = ss.accept();

      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);
      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);

      String inputLine;
      inputLine = r.nextLine();

      // send String back to the client
      p.println("Hello " + inputLine + " from Hello Server");
      p.close();
    }
  }
}
