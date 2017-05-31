package pl.edu.wat.tim.webstore.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//Receiver is also known as a message driven POJO.
//The JmsListener annotation defines the name of the Destination that this method should listen to
//and the reference to the JmsListenerContainerFactory to use to create the underlying message listener container
//JmsListenerContainerFactory<C extends MessageListenerContainer> - Factory of MessageListenerContainer based on a JmsListenerEndpoint definition.
//MessageListenerContainer comes handy when we want to implement asynchronous message reception.
//
@Component
public class ResponseReceiver {

    private static final String QUEUE = "order-response-mailbox";

    //@JmsListener which marks a method to be the target of a JMS message listener on the specified destination().
    //1. Configure a message-listener-container [ with JmsListenerContainerFactory] : which listens on a destination
    //[can be the one used with @JmsListener] and when any message arrives on this destination, it retrieved that message
    // and passes to the bean annotated with @JmsListener for that destination.
    //2. Use @EnableJms which enables detection of JmsListener annotations on any Spring-managed bean in the container.
    //containerFactory attribute defining the name of the JmsListenerContainerFactory bean to use.
    // When none is set a JmsListenerContainerFactory bean with name jmsListenerContainerFactory is assumed to be present.
    @JmsListener(destination = QUEUE, containerFactory = MessagingListenerConfig.FACTORY)
    public void receiveMessage() {

    }
}
