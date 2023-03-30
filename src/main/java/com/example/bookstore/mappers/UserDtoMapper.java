package com.example.bookstore.mappers;

import com.example.bookstore.dto.user.UserDto;
import com.example.bookstore.model.user.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractConverter<User, UserDto> {
    @Override
    protected UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
}
