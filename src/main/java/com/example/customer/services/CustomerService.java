package com.example.customer.services;

import com.example.customer.entity.Customer;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.model.CustomerRequestDTO;
import com.example.customer.model.CustomerResponseDTO;
import com.example.customer.model.CustomerStatusDTO;
import com.example.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO req) {
        if (customerRepository.existsByDocumentTypeAndDocumentNumber(
                req.getDocumentType(), req.getDocumentNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Customer already exists for document: " + req.getDocumentType() + "-" + req.getDocumentNumber());
        }

        Customer entity = customerMapper.toEntity(req);

        if (entity.getCustomerId() == null) {
            entity.setCustomerId(UUID.randomUUID());
        }
        if (entity.getContacts() != null) {
            entity.getContacts().forEach(contactData -> {
                if (contactData.getContactId() == null) contactData.setContactId(UUID.randomUUID());
                if (contactData.getCustomer() == null) contactData.setCustomer(entity);
            });
        }

        try {
            Customer saved = customerRepository.save(entity);
            return customerMapper.toResponseDto(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists", ex);
        }

    }

    public CustomerStatusDTO checkStatus(String documentType, String documentNumber) {
        Customer customer = customerRepository
                .findByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found for document: %s-%s".formatted(documentType, documentNumber)));

        boolean active = "A".equalsIgnoreCase(customer.getStatus());
        if (!active) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Customer exists but is not active (status=" + customer.getStatus() + ")");
        }

        return new CustomerStatusDTO(customer.getCustomerId(), true, true);
    }

    public CustomerResponseDTO getCustomerByDocument(String documentType, String documentNumber) {
        Customer customer = customerRepository
                .findByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found for document: %s-%s".formatted(documentType, documentNumber)));

        return customerMapper.toResponseDto(customer);
    }

    public List<CustomerResponseDTO> getAllCustomer(){
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }
}

