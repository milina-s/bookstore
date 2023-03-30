package com.example.bookstore.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase and one uppercase letter, no spaces and be 8 characters long")
    private String password;

    private String firstname;
    private String lastname;
    private String address;

    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phone;
}
