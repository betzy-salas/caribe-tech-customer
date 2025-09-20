package com.example.customer.model;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerResponseDTO {
    private UUID customerId;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String status;
    private List<ContactDataDTO> contacts;
}

