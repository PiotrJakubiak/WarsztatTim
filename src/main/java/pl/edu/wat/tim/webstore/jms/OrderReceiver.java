package pl.edu.wat.tim.webstore.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.configuration.MessagingListenerConfig;
import pl.edu.wat.tim.webstore.model.InventoryResponse;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.OrderService;

@Component
public class OrderReceiver {

    private static final String QUEUE = "order-mailbox";

    private OrderService orderService;
    private ResponseSender responseSender;

    @Autowired
    public OrderReceiver(OrderService orderService, ResponseSender responseSender){
        this.orderService = orderService;
        this.responseSender = responseSender;
    }

    @JmsListener(destination = QUEUE, containerFactory = MessagingListenerConfig.FACTORY)
    public void receiveMessage(final Message<Product> message){
        Product product = message.getPayload();
        InventoryResponse response = orderService.processOrder(product);
        responseSender.sendMessage(response);
    }
}
