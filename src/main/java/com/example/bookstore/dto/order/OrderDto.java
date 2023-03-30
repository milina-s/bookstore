package com.example.bookstore.dto.order;

import com.example.bookstore.dto.user.CustomerDto;
import com.example.bookstore.dto.user.ManagerDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id",
        "status",
        "orderBooks",
        "totalPrice",
        "totalPriceWithDiscount",
        "customer",
        "manager",
        "address",
        "createdAt",
        "updatedAt",})
public class OrderDto implements Serializable {

    private Long id;

    private String status;

    private List<OrderBookDto> orderBooks;

    private String address;

    private String createdAt;

    private String updatedAt;

    private CustomerDto customer;

    private ManagerDto manager;

    private Double totalPrice;

    private Double totalPriceWithDiscount;
}
