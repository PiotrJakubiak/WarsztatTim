package pl.edu.wat.tim.webstore.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.InventoryResponse;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.ProductService;

import java.util.concurrent.Future;

@Component
public class JmsRunner implements CommandLineRunner {

    private ProductService productService;
    private OrderSender orderSender;

    @Autowired
    public JmsRunner(ProductService productService, OrderSender orderSender){
        this.productService = productService;
        this.orderSender = orderSender;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0 ; i < 20 ; i++)
            runMessagesProcess();
    }


    @Async
    private void runMessagesProcess(){
        Product product = productService.getProductById(1);
        orderSender.sendMessage(product);
    }
}
