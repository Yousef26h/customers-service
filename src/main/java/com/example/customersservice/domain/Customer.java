package com.example.customersservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private String customerNumber;

    private String name;

    private String phone;

    private String email;

    private String street;

    private String city;

    private String zip;
}
