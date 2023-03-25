package com.example.bookstore.mappers;

import com.example.bookstore.dto.CustomerDto;
import com.example.bookstore.model.user.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper extends AbstractConverter<User, CustomerDto> {
    @Override
    protected CustomerDto convert(User customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .build();
    }
}
