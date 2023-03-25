package com.example.bookstore.mappers;

import com.example.bookstore.dto.ManagerDto;
import com.example.bookstore.model.user.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ManagerDtoMapper extends AbstractConverter<User, ManagerDto> {
    @Override
    protected ManagerDto convert(User manager) {
        return ManagerDto.builder()
                .id(manager.getId())
                .firstname(manager.getFirstname())
                .lastname(manager.getLastname())
                .build();
    }
}