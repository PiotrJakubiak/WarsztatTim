package pl.edu.wat.tim.webstore.service;



import pl.edu.wat.tim.webstore.model.Customer;

import java.util.List;

/**
 * Created by Piotr on 09.05.2017.
 */
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

    void addCustomer(Customer customer);

    void deleteCustomer(int customer);
}
