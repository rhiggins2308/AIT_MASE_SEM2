package mase.tus.labs.jms.receiver;

// Q3

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

class MyMessageReceiver implements MessageListener {

  public void onMessage(Message message) {
    try {
      System.out.println("onMessage() called ");

      String messageText = null;
      if (message instanceof TextMessage)
        messageText = ((TextMessage) message).getText();
      System.out.println(messageText);

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
