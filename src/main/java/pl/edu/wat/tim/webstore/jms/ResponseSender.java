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

    void sendMessage(final InventoryResponse inventoryResponse){
        jmsTemplate.send(QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(inventoryResponse);
            }
        });
    }
}
