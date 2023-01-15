package com.example.customersservice;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Commit
    @Test
    @Order(1)
    void createCustomer() throws Exception {

        String json = "{\"name\": \"Yousef Salem\", \"phone\": \"6415314377\", \"email\": \"yousefsalem276@gmail.com\", \"street\": \"1000 N\", \"city\": \"Fairfield\", \"zip\": \"52557\"}";

        mockMvc.perform(
                        post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isCreated()
                );

        json = "{\"name\": \"Ahmad Salem\", \"phone\": \"6415314378\", \"email\": \"ahmad.salem@gmail.com\", \"street\": \"1000 N\", \"city\": \"Cairo\", \"zip\": \"52557\"}";

        mockMvc.perform(
                        post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isCreated()
                );
    }

    @Test
    @Order(2)
    void getAllCustomers() throws Exception {
        mockMvc.perform(
                get("/customers")
        ).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getCustomerById() throws Exception {
        String customerId = "6392b63c29e2347eefd90001";
        String json = "{\"name\": \"Ahmad Salem\", \"phone\": \"6415314378\", \"email\": \"ahmad.salem@gmail.com\", \"street\": \"1000 N\", \"city\": \"Cairo\", \"zip\": \"52557\"}";
        mockMvc.perform(
                        get("/customers/{id}", customerId)
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

        String fakeCustomerId = "6392b63c29e2347eefd911";
        mockMvc.perform(
                get("/customers/{id}", fakeCustomerId)
        ).andExpect(status().isNotFound());
    }

}
