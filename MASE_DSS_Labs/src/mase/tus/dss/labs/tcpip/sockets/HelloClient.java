package mase.tus.dss.labs.tcpip.sockets;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class HelloClient {

  public static void main(String[] args) throws Exception {

    InetAddress inet = InetAddress.getByName("localhost");
    Socket s = new Socket(inet, 2000);

    OutputStream o = s.getOutputStream();
    PrintWriter p = new PrintWriter(o);
    InputStream in = s.getInputStream();
    Scanner r = new Scanner(in);

    p.println("Robbie");
    p.flush();

    String inputLine = r.nextLine();
    System.out.println("Client: " + inputLine);
  }
}