package pl.edu.wat.tim.webstore.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.Product;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class OrderSender {

    private static final String QUEUE = "order-mailbox";

    private JmsTemplate jmsTemplate;

    @Autowired
    public OrderSender(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final Product product){
        jmsTemplate.send(QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return  session.createObjectMessage(product);
            }
        });
    }
}
