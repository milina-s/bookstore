package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

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
