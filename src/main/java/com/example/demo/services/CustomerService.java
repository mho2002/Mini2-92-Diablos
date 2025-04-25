package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id)
    {
        if (customerRepository.findById(id).isPresent())
            return customerRepository.findById(id).get();
        return null;
    }

    public Customer updateCustomer(Long id, Customer customer)
    {
        Customer customerToUpdate = new Customer();
        if (customerRepository.findById(id).isPresent())
            customerToUpdate = customerRepository.findById(id).get();
        else
            return null;
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhoneNumber(String.valueOf(customer.getPhoneNumber()));
        customerToUpdate.setTrips(customer.getTrips());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(Long id)
    {
        if (customerRepository.findById(id).isPresent())
            customerRepository.deleteById(id);
    }

    public List<Customer> findCustomersByEmailDomain(String domain)
    {
        return customerRepository.findByEmailDomain(domain);
    }

    public List<Customer> findCustomersByPhonePrefix(String prefix)
    {
        return customerRepository.findByPhonePrefix(prefix);
    }

    public boolean validateCustomerData (Customer customer)
    {
        if (customer.getEmail()==null || customer.getEmail().isEmpty())
            return false;
        else if (customer.getName()==null || customer.getName().isEmpty()) {

            return false;

        } else
            return customer.getPhoneNumber() != null && !customer.getPhoneNumber().isEmpty();
    }

}