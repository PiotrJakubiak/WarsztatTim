package pl.edu.wat.tim.webstore.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.ProductService;

@Component
public class JmsRunner{

    private ProductService productService;
    private OrderSender orderSender;

    @Autowired
    public JmsRunner(ProductService productService, OrderSender orderSender){
        this.productService = productService;
        this.orderSender = orderSender;
    }

    @Scheduled(initialDelay = 3000, fixedRate = 3000)
    private void runMessages() throws InterruptedException{
        Product product = productService.getProductById(1);
        orderSender.sendMessage(product);
    }
}
