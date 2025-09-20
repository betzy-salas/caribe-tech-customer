package com.example.customer.repository;

import com.example.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
    Optional<Customer> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
