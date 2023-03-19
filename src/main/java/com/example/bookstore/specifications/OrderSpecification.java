package com.example.bookstore.specifications;

import com.example.bookstore.model.order.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrderSpecification {
    public static Specification<Order> hasCustomer(Long customerId) {
        return (root, query, builder) -> builder.equal(root.get("customer").get("id"), customerId);
    }

    public static Specification<Order> hasManager(Long managerId) {
        return (root, query, builder) -> builder.equal(root.get("manager").get("id"), managerId);
    }

    public static Specification<Order> hasStatus(String status) {
        return (root, query, builder) -> builder.equal(root.get("status"), status);
    }

    public static Specification<Order> hasBooks(List<Long> bookIds) {
        return (root, query, builder) -> root.join("orderBooks").get("book").get("id").in(bookIds);
    }
    public static Specification<Order> createdAfter(Long date) {
        return (root, query, builder) -> builder.greaterThan(root.get("createdAt"), date);
    }

}
