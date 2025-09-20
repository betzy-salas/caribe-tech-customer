package com.example.customer.mapper;

import com.example.customer.entity.ContactData;
import com.example.customer.entity.Customer;
import com.example.customer.model.CustomerRequestDTO;
import com.example.customer.model.CustomerResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { ContactDataMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CustomerMapper {

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "contacts", source = "contacts")
    public abstract Customer toEntity(CustomerRequestDTO dto);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "contacts", source = "dto.contacts")
    public abstract void updateEntity(@MappingTarget Customer entity, CustomerRequestDTO dto);

    public abstract CustomerResponseDTO toResponseDto(Customer entity);
    public abstract List<CustomerResponseDTO> toResponseDtoList(List<Customer> entities);

    @AfterMapping
    protected void linkContacts(@MappingTarget Customer customer) {
        if (customer.getContacts() != null) {
            for (ContactData c : customer.getContacts()) c.setCustomer(customer);
        }
    }

    @AfterMapping
    protected void linkContactsOnUpdate(CustomerRequestDTO dto, @MappingTarget Customer entity) {
        if (entity.getContacts() != null) {
            for (ContactData c : entity.getContacts()) c.setCustomer(entity);
        }
    }
}
