package pl.edu.wat.tim.webstore.repository.impl.orm;

import pl.edu.wat.tim.webstore.model.Customer;
import pl.edu.wat.tim.webstore.repository.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Piotr on 10.05.2017.
 */
@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getAllCustomers() {
        String hql = "FROM Customer as cust ORDER BY cust.customerId";
        return (List<Customer>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);

    }

    @Override
    public void updateCustomer(Customer customer) {
        Customer cust = getCustomerById(customer.getId());
        cust.setName(cust.getName());
        cust.setEmail(cust.getEmail());
        entityManager.flush();
    }

    @Override
    public void deleteCustomer(int customerId) {
        entityManager.remove(getCustomerById(customerId));
    }

}

