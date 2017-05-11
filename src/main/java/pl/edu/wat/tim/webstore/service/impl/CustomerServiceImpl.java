package pl.edu.wat.tim.webstore.service.impl;

import pl.edu.wat.tim.webstore.model.Customer;
import pl.edu.wat.tim.webstore.repository.CustomerRepository;
import pl.edu.wat.tim.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr on 09.05.2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }
    @Override
    public void deleteCustomer(int customerId) {
        customerRepository.deleteCustomer(customerId);
    }
}



