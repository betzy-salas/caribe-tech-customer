package com.example.customer.mapper;

import com.example.customer.entity.ContactData;
import com.example.customer.model.ContactDataDTO;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ContactDataMapper {

    ContactDataDTO toDto(ContactData entity);

    List<ContactDataDTO> toDtoList(Collection<ContactData> entities);

    @Mapping(target = "customer", ignore = true)
    ContactData toEntity(ContactDataDTO dto);

    List<ContactData> toEntityList(Collection<ContactDataDTO> dtos);

    @Mapping(target = "customer", ignore = true)
    void updateEntity(@MappingTarget ContactData entity, ContactDataDTO dto);
}

