package com.example.bookstore.repositories;

import com.example.bookstore.model.order.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
}
