package com.example.bookstore.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"isbn", "quantity"})
public class OrderBookDto implements Serializable {

    @Pattern(regexp = "^[0-9]{10}([0-9]{3})?$", message = "Wrong ISBN. ISBN must contain only numbers and be 10 or 13 characters long")
    private String isbn;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
