package mase.tus.labs.jms.receiver;

// Q3

import java.util.Hashtable;

import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SendToQueue {

  public static void main(String[] args) {
    try {
      System.out.println("SendToQueue starting ..");

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
      QueueSender qSender = qSession.createSender(q);

      // send a message to the queue
      TextMessage message = qSession.createTextMessage();
      message.setText("Hello there.");
      qSender.send(message);

      System.out.println("SendToQueue has sent to Q");
      qc.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}