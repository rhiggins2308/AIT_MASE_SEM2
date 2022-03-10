package mase.tus.labs.threads;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

class TPWorkerThread extends Thread {
  BlockingQueue<Socket> queue;

  TPWorkerThread(BlockingQueue<Socket> b) {
    this.queue = b;
  }

  public void run() {
    try {
      while (true) {
        Socket s = queue.take();

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
          p.println(f.nextLine());
        }
        sleep(15000) ;
        p.close();
        System.out.println(getName() + " finished handling " + fileName);
      }
    } catch (Exception e) {
      System.out.println("an exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
