package mase.tus.labs.jms.main;

// Q1

import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SynchronousReceiver {

  public static void main(String[] args) {

    try {
      System.out.println("PullReceiver starting ..");

      Hashtable<String, String> properties = new Hashtable<String, String>();
      properties.put(Context.INITIAL_CONTEXT_FACTORY,
          "org.exolab.jms.jndi.InitialContextFactory");
      properties.put(Context.PROVIDER_URL, "rmi://localhost:1099/");
      Context context = new InitialContext(properties);
      QueueConnectionFactory qcf = (QueueConnectionFactory) context
          .lookup("JmsQueueConnectionFactory");
      QueueConnection qc = qcf.createQueueConnection();
      QueueSession qSession = qc.createQueueSession(false,
          Session.AUTO_ACKNOWLEDGE);
      javax.jms.Queue q = (javax.jms.Queue) context.lookup("queue1");

      QueueReceiver queueReceiver = qSession.createReceiver(q);
      qc.start();

      Message message = queueReceiver.receive();
      String messageText = null;
      if (message instanceof TextMessage)
        messageText = ((TextMessage) message).getText();
      System.out.println(messageText);
      qc.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}