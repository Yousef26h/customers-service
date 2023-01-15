package com.example.customersservice.service;

import com.example.customersservice.domain.Customer;

import java.util.List;


public interface CustomerService {

    void createCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(String id);
}
