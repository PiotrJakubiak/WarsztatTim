package pl.edu.wat.tim.webstore.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.wat.tim.webstore.model.InventoryResponse;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public InventoryResponse processOrder(Product product) {
        return prepareResponse(product);
    }

    private InventoryResponse prepareResponse(Product product){
        InventoryResponse response = new InventoryResponse();
        response.setProductId(product.getProductId());
        response.setReturnCode(200);
        response.setComment("Order Processed successfully");
        return response;
    }
}
