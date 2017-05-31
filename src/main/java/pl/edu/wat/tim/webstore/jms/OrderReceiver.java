package pl.edu.wat.tim.webstore.jms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.Product;

@Component
public class OrderReceiver {

    private static final String QUEUE = "order-mailbox";

    @JmsListener(destination = QUEUE, containerFactory = MessagingListenerConfig.FACTORY)
    public void receiveMessage(final Message<Product> message){
        MessageHeaders headers =  message.getHeaders();
        System.out.println("Application : headers received : {}" + headers.toString());

        Product product = message.getPayload();
        System.out.println("Application : product : {}" + product);
    }
}
