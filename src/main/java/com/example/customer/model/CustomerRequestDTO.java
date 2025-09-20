package com.example.customer.model;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerRequestDTO {
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String status;
    private List<ContactDataDTO> contacts;
}

