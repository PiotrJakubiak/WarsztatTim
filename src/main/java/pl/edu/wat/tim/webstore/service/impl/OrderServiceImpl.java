package pl.edu.wat.tim.webstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.tim.webstore.jms.ResponseSender;
import pl.edu.wat.tim.webstore.model.InventoryResponse;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private ResponseSender responseSender;

    @Autowired
    public OrderServiceImpl(ResponseSender responseSender){
        this.responseSender = responseSender;
    }
    @Override
    public void processOrder(Product product) {
        InventoryResponse response = prepareResponse(product);
        responseSender.sendMessage(response);
    }

    private InventoryResponse prepareResponse(Product product){
        InventoryResponse response = new InventoryResponse();
        response.setProductId(product.getProductId());
        response.setReturnCode(200);
        response.setComment("Order Processed successfully");
        return response;
    }
}
