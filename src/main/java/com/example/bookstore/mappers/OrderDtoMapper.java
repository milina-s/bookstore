package com.example.bookstore.mappers;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.dto.OrderBookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor

@Component
public class OrderDtoMapper extends AbstractConverter<Order, OrderDto> {
    private final CustomerDtoMapper customerDtoMapper;
    private final ManagerDtoMapper managerDtoMapper;

    @Override
    protected OrderDto convert(Order order) {
        System.out.println("OrderDtoMapper.convert");

        return OrderDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .orderBooks(order.getOrderBooks().stream()
                        .map(orderBook -> OrderBookDto.builder()
                                .isbn(orderBook.getBook().getIsbn())
                                .quantity(orderBook.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .updatedAt(order.getUpdatedAt() == null ? null : order.getUpdatedAt().toString())
                .createdAt(order.getCreatedAt().toString())
                .address(order.getAddress())
                .customer(customerDtoMapper.convert(order.getCustomer()))
                .manager(order.getManager() == null ? null : managerDtoMapper.convert(order.getManager()))
                .totalPrice(order.getOrderBooks().stream()
                        .map(orderBook -> orderBook.getBook().getPrice() * orderBook.getQuantity())
                        .reduce(0.0, Double::sum))
                .totalPriceWithDiscount(order.getOrderBooks().stream()
                        .map(orderBook -> {
                            if (orderBook.getBook().getPriceWithDiscount() == null) {
                                return orderBook.getBook().getPrice() * orderBook.getQuantity();
                            } else {
                                return orderBook.getBook().getPriceWithDiscount() * orderBook.getQuantity();
                            }
                        })
                        .reduce(0.0, Double::sum))
                .build();
    }
}
