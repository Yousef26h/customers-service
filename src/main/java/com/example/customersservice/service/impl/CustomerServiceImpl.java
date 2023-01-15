package com.example.customersservice.service.impl;

import com.example.customersservice.adapter.repository.CustomerRepository;
import com.example.customersservice.domain.Customer;
import com.example.customersservice.exception.NoCustomerFoundException;
import com.example.customersservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> all = customerRepository.findAll();
        return all;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new NoCustomerFoundException("There is no customer found with id: " + id));
    }

}
