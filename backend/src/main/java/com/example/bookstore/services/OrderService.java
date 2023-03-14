package com.example.bookstore.services;

import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.OrderBook;
import com.example.bookstore.model.order.OrderStatus;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.OrderBookRepository;
import com.example.bookstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;

    public void createOrder(List<OrderBook> orderBooks, User user) {

        Order order = orderRepository.save(Order.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .build());

        orderBooks.forEach(orderBook -> {
            orderBook.setOrder(order);
            orderBookRepository.save(orderBook);
        });

    }

    public void setOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
