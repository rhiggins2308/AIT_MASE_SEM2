package mase.tus.labs.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedFileServerApp {

  public static void main(String args[]) {

    try {
		System.out.println("Server ready");

		ServerSocket ss = new ServerSocket(2001);
		while (true) {
		  Socket s = ss.accept();
		  Thread t = new WorkerThread(s);
		  t.start();
		}
	} catch (IOException ioe) {
		ioe.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}

class WorkerThread extends Thread {
  Socket s;

  WorkerThread(Socket s) {
    this.s = s;
  }

  public void run() {
    try {
      InputStream in = s.getInputStream();
      Scanner r = new Scanner(in);
      OutputStream o = s.getOutputStream();
      PrintWriter p = new PrintWriter(o);

      String fileName;
      fileName = r.nextLine();
      System.out.println(getName() + " handling " + fileName);

      FileInputStream fin = new FileInputStream(fileName);
      Scanner f = new Scanner(fin);

      String line;
      while (f.hasNextLine()) {
        line = f.nextLine();
        p.println(line);
      }
      sleep(15000);
      p.close();
      System.out.println(getName() + " finished handling " + fileName);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
