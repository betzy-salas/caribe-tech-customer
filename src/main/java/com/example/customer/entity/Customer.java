package com.example.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false, columnDefinition = "UUID")
    @EqualsAndHashCode.Include
    private UUID customerId;

    @Column(name = "document_type", length = 2)
    private String documentType;

    @Column(name = "document_number", length = 20)
    private String documentNumber;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "status", length = 1)
    private String status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ContactData> contacts = new LinkedHashSet<>();
}

