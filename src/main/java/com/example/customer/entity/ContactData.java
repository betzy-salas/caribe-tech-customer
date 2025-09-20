package com.example.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Table(name = "contact_data")
public class ContactData {

    @Id
    @Column(name = "contact_id", nullable = false, updatable = false, columnDefinition = "UUID")
    @EqualsAndHashCode.Include
    private UUID contactId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "data_type", length = 2)
    private String dataType;

    @Column(name = "data", length = 100)
    private String data;

    @Column(name = "status", length = 1)
    private String status;
}

