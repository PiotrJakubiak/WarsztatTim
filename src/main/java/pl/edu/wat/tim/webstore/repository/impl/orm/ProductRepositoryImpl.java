package pl.edu.wat.tim.webstore.repository.impl.orm;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Repository;
import pl.edu.wat.tim.webstore.exception.ProductNotFoundException;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Piotr on 22.05.2017.
 */
@Transactional
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Repository
public class ProductRepositoryImpl implements ProductRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        String hql = "FROM Product as product";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Product getProductById(int productID) {
        Product product = entityManager.find(Product.class, productID);
        if(product == null) {
            throw new ProductNotFoundException(productID);
        }
        return product;
    }

    @Override
    public void addProduct(Product product) {
            entityManager.persist(product);
    }

    @Override
    public void deleteProduct(int productId) {
            entityManager.remove(getProductById(productId));
    }

}
