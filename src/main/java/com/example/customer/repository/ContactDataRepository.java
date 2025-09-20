package com.example.customer.repository;

import com.example.customer.entity.ContactData;
import com.example.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactDataRepository extends JpaRepository<ContactData, UUID> {
    List<ContactData> findByCustomer(Customer customer);
}

