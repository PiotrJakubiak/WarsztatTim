package pl.edu.wat.tim.webstore.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
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
        MessageHeaders headers =  message.getHeaders();
        System.out.println("Application : headers received : {}" + headers.toString());

        Product product = message.getPayload();
        System.out.println("Application : Product :" + product.toString());

        InventoryResponse response = orderService.processOrder(product);
        responseSender.sendMessage(response);
    }
}
