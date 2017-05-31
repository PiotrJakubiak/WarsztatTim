package pl.edu.wat.tim.webstore.service;

import pl.edu.wat.tim.webstore.model.InventoryResponse;
import pl.edu.wat.tim.webstore.model.Product;

public interface OrderService {

    InventoryResponse processOrder(Product product);
}


