package com.example.customer.model;

import lombok.*;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContactDataDTO {
    private UUID contactId;
    private String dataType;
    private String data;
    private String status;
}

