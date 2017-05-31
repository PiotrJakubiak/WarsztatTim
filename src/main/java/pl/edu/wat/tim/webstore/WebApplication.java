package pl.edu.wat.tim.webstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.wat.tim.webstore.jms.Email;
import pl.edu.wat.tim.webstore.jms.OrderSender;
import pl.edu.wat.tim.webstore.jms.ResponseSender;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.ProductService;


@EnableCaching
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class WebApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);

        ProductService productService = (ProductService) context.getBean("productService");
        Product product = productService.getProductById(0);
        OrderSender orderSender = context.getBean(OrderSender.class);
        orderSender.sendMessage(new Email("info@example.com", "Hello"));
    }

}

