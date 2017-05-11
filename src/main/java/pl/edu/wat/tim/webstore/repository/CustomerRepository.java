package pl.edu.wat.tim.webstore.repository;


import pl.edu.wat.tim.webstore.model.Customer;

import java.util.List;

/**
 * Created by Piotr on 10.05.2017.
 */
public interface CustomerRepository {

    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
