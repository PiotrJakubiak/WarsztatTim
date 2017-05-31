package pl.edu.wat.tim.webstore.service.impl;

import pl.edu.wat.tim.webstore.jms.ResponseReceiver;
import pl.edu.wat.tim.webstore.jms.ResponseSender;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.repository.ProductRepository;
import pl.edu.wat.tim.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr on 09.05.2017.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ResponseSender responseSender;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(int productID) {

        return productRepository.getProductById(productID);
    }

    @Override
    public void addProduct(Product product) {
       productRepository.addProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public void sendProduct(Product product) {
//        responseSender.sendMessage(product);
    }
}
