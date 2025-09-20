package com.example.customer.model;

import java.util.UUID;

public record CustomerStatusDTO(
        UUID customerId,
        boolean exists,
        boolean active
) {}
