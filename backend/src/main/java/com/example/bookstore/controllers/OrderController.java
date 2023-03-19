package com.example.bookstore.controllers;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.OrderBookDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.order.OrderStatus;
import com.example.bookstore.model.user.User;
import com.example.bookstore.services.OrderService;
import com.example.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody List<OrderBookDto> orderBooks, Principal principal) {
        return orderService.createOrder(orderBooks, userService.findByEmail(principal.getName()));
    }

    @PutMapping("/{orderId}/status")
    public void setOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status, Principal principal) {

        User user = userService.findByEmail(principal.getName());

        if (!user.getRole().equals(status.getRole()))
            throw new RuntimeException(ErrorMessage.USER_NOT_ALLOWED_TO_CHANGE_ORDER_STATUS);

        orderService.setOrderStatus(orderId, status, user);
    }

    // TODO: add role check for admin
    @GetMapping("/filter/admin")
    public List<OrderDto> filterOrders(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "customer", required = false) Long customerId,
            @RequestParam(name = "manager", required = false) Long managerId,
            @RequestParam(name = "createdAfter", required = false) Long createdAfter,
            // sort
            @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortdir", defaultValue = "desc") String sortDirection,
            // pagination
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return orderService.filterOrders(status, customerId, managerId, createdAfter, pageable);
    }

    @GetMapping("/filter/customer")
    public List<OrderDto> filterOrdersByCustomer(
            Principal principal,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "createdAfter", required = false) Long createdAfter,
            // sort
            @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortdir", defaultValue = "desc") String sortDirection,
            // pagination
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return orderService.filterOrders(status, userService.findByEmail(principal.getName()).getId(), null, createdAfter, pageable);
    }

    @GetMapping("/filter/manager")
    public List<OrderDto> filterByManager(
            Principal principal,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "customer", required = false) Long customerId,
            @RequestParam(name = "createdAfter", required = false) Long createdAfter,
            // sort
            @RequestParam(name = "sortby", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortdir", defaultValue = "desc") String sortDirection,
            // pagination
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return orderService.filterOrders(status, customerId, userService.findByEmail(principal.getName()).getId(), createdAfter, pageable);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId, Principal principal) {
        if (userService.findByEmail(principal.getName()).getRole().name().equals("ROLE_ADMIN") ||
                orderService.isOrderBelongsToUser(orderId, userService.findByEmail(principal.getName()).getId()))
            return orderService.findOrderDto(orderId);
        else
            throw new RuntimeException(ErrorMessage.USER_NOT_ALLOWED_TO_GET_ORDER);
    }
}
