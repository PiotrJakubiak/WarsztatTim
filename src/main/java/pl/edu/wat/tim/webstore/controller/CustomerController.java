package pl.edu.wat.tim.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wat.tim.webstore.model.Customer;
import pl.edu.wat.tim.webstore.repository.CustomerRepository;
import pl.edu.wat.tim.webstore.service.CustomerService;

/**
 * Created by Piotr on 09.05.2017.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/all")
    public ModelAndView allCustomers() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("customers", customerService.getAllCustomers());
        modelAndView.setViewName("customers");
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public void getArticleById(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        System.out.println(customer.toString());

    }
}
