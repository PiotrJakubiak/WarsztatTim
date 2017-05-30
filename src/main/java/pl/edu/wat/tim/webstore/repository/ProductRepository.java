package pl.edu.wat.tim.webstore.repository;

import pl.edu.wat.tim.webstore.model.Product;

import java.util.List;

/**
 * Created by Piotr on 09.05.2017.
 */

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(int productID);
    void addProduct(Product product);
    void deleteProduct(int productId);

}
