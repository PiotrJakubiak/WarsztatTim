package pl.edu.wat.tim.webstore.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.InventoryResponse;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ResponseSender {

    private static final String QUEUE = "order-response-mailbox";

    private JmsTemplate jmsTemplate;

    @Autowired
    public ResponseSender(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final InventoryResponse inventoryResponse){
        // Send a message with a POJO - the template reuse the message converter
        //Two beans that you don’t see defined are JmsTemplate and ConnectionFactory. These are created automatically by Spring Boot.
        // In this case, the ActiveMQ broker runs embedded.
        //Destination: Destinations needs to be configured for both sending and receiving ends.
        // ActiveMQ comes up with builin implementations for Queue and Topic which can accept a String [QUEUE or Topic name] as an argument.
        //By default, Spring Boot creates a JmsTemplate configured to transmit to queues
        jmsTemplate.send(QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //Create a JMS ObjectMessage for the given Serializable object.
                return session.createObjectMessage(inventoryResponse);
            }
        });
    }
}
