package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        if (customerService.validateCustomerData(customer))
            return customerService.addCustomer(customer);
        else
            throw new IllegalArgumentException("Invalid Customer data");
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id)
    {
        if (id==null)
            throw new IllegalArgumentException( "Customer id cannot be null" );
        Customer customer = customerService.getCustomerById(id);
        if (customer==null)
            throw new IllegalArgumentException( "Customer not found" );
        return customer;
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    {
        if (id==null)
            throw new IllegalArgumentException( "Customer id cannot be null" );
        if (customerService.validateCustomerData(customer))
            return customerService.updateCustomer(id, customer);
        else
            throw new IllegalArgumentException("Invalid Customer data");

    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id)
    {
        if (id==null)
            throw new IllegalArgumentException( "Customer id cannot be null" );
        Customer customer = customerService.getCustomerById(id);
        if (customer==null)
            return "Customer cannot be found";
        customerService.deleteCustomer(id);
        return "Customer deleted";
    }

    @GetMapping("/findByEmailDomain")
    public List<Customer> findCustomersByEmailDomain(@RequestParam String domain)
    {
        if (domain==null || domain.isEmpty())
            throw new IllegalArgumentException( "Customer domain cannot be null or empty" );
        return customerService.findCustomersByEmailDomain(domain);
    }

    @GetMapping("/findByPhonePrefix")
    public List<Customer> findCustomersByPhonePrefix(@RequestParam String prefix)
    {
        if (prefix==null || prefix.isEmpty())
            throw new IllegalArgumentException( "Customer phone prefix cannot be null or empty" );
        return customerService.findCustomersByPhonePrefix(prefix);
    }


}
