package com.example.customersservice;

import com.example.customersservice.adapter.repository.CustomerRepository;
import com.example.customersservice.domain.Customer;
import com.example.customersservice.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UnitTests {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void WhenCreateCustomer_ExpectedMethodInvokedOnce() {
        Customer customer = Customer.builder()
                .customerNumber("12345")
                .name("Ahmad")
                .email("ahmad.salem@gmail.com")
                .city("Cairo")
                .phone("0790000")
                .zip("00000")
                .street("Street")
                .build();

        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.createCustomer(customer);

        verify(customerRepository, times(1)).save(customer);

    }

    @Test
    public void WhenGetCustomers_ExpectedListOfCustomers() {

        List<Customer> expectedList = new ArrayList<>() {{
            add(Customer.builder()
                    .name("Ahmad")
                    .customerNumber("12345")
                    .email("ahmad.salem@gmail.com")
                    .city("Cairo")
                    .phone("0790000")
                    .zip("00000")
                    .street("Street")
                    .build());
        }};
        List<Customer> mockList = new ArrayList<>() {{
            add(Customer.builder()
                    .name("Ahmad")
                    .customerNumber("12345")
                    .email("ahmad.salem@gmail.com")
                    .city("Cairo")
                    .phone("0790000")
                    .zip("00000")
                    .street("Street")
                    .build());
        }};

        when(customerRepository.findAll()).thenReturn(mockList);

        List<Customer> actualList = customerService.getCustomers();

        assertEquals(expectedList, actualList);
    }

    @Test
    public void WhenGetCustomerById_ExpectedCustomerWithSameId() {
        Customer mockedCustomer = Customer.builder()
                .customerNumber("6392b63c29e2347eefd90001")
                .name("Ahmad Salem")
                .city("Cairo")
                .email("ahmad.salem@gmail.com")
                .zip("52557")
                .street("1000 N")
                .phone("6415314378")
                .build();
        String customerId = "6392b63c29e2347eefd90001";
        when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(mockedCustomer));
        Customer customerById = customerService.getCustomerById(customerId);
        assertEquals(mockedCustomer, customerById);
    }
}
