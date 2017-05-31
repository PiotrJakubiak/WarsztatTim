package pl.edu.wat.tim.webstore.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.Product;

@Component
public class OrderReceiver {

    private static final String QUEUE = "order-mailbox";

    private JmsTemplate jmsTemplate;

    @Autowired
    public OrderReceiver(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = QUEUE)
    public void receiveMessage(final Email message){
//        MessageHeaders headers =  message.getHeaders();
//        System.out.println("Application : headers received : {}" + headers.toString());
        System.out.println("Received <" + message + ">");
    }
}
