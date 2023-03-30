package com.example.bookstore.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "firstname", "lastname"})
public class ManagerDto implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;
}
