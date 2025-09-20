package com.example.customer.controller;

import com.example.customer.model.CustomerRequestDTO;
import com.example.customer.model.CustomerResponseDTO;
import com.example.customer.model.CustomerStatusDTO;
import com.example.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO request) {
        var created = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public CustomerStatusDTO checkStatus(
            @RequestParam String documentType,
            @RequestParam String documentNumber) {

        return customerService.checkStatus(documentType, documentNumber);
    }

    @GetMapping("/{documentType}/{documentNumber}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDTO returnCustomer(
            @PathVariable String documentType,
            @PathVariable String documentNumber) {

        return customerService.getCustomerByDocument(documentType, documentNumber);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomerResponseDTO>> returnAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

}

