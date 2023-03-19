package com.example.bookstore.repositories;

import com.example.bookstore.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findByCustomerId(Long customerId);
    boolean existsByIdAndCustomerIdOrManagerId (Long id, Long customerId, Long managerId);

    List<Order> findByManagerId(Long managerId);
}
