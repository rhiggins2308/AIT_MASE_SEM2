package mase.tus.labs.threads;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPoolFileServer {

  public static void main(String args[]) throws Exception {

    System.out.println("Server ready");

    BlockingQueue<Socket> queue = new ArrayBlockingQueue<Socket>(10);
    ServerSocket ss = new ServerSocket(2001);
    for (int i = 0; i < 4; i++) {
      TPWorkerThread t = new TPWorkerThread(queue);
      t.start();
    }

    while (true) {
      Socket s = ss.accept();
      queue.put(s);
    }

  }
}
