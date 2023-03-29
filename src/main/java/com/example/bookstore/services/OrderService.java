package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.CategoryDtoRequest;
import com.example.bookstore.dto.OrderBookDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.OrderBook;
import com.example.bookstore.model.order.OrderStatus;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.OrderRepository;
import com.example.bookstore.specifications.OrderSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Slf4j

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public void setStatusByManager(Long orderId, OrderStatus status, User manager) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public void setStatusByCustomer(Long orderId, OrderStatus status, User customer) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public OrderDto createOrder(List<OrderBookDto> orderBookDtos, User user) {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setCustomer(user);
        order.setOrderBooks(orderBookDtos.stream().map(bookDto -> {
            Book book = bookService.findBookByIsbn(bookDto.getIsbn());
            OrderBook orderBook = new OrderBook();
            orderBook.setBook(book);
            orderBook.setQuantity(bookDto.getQuantity());
            orderBook.setOrder(order);
            orderBook.setPrice(book.getPrice());
            orderBook.setPriceWithDiscount(book.getPriceWithDiscount());
            return orderBook;
        }).collect(Collectors.toList()));
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND_BY_ID + id)
        );
    }

    public void setOrderStatus(Long orderId, OrderStatus status, User user) {
        Order order = findById(orderId);
        if (user.getRole().name().equals("ROLE_CUSTOMER"))
            order.setCustomer(user);
        else if (user.getRole().name().equals("ROLE_MANAGER"))
            order.setManager(user);
        else
            throw new RuntimeException(ErrorMessage.USER_NOT_ALLOWED_TO_CHANGE_ORDER_STATUS);
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    public List<OrderDto> findAllOrderShortDtoResponse() {
        return modelMapper.map(orderRepository.findAll(), new TypeToken<List<CategoryDtoRequest>>() {
        }.getType());
    }

    public List<OrderDto> filterOrders(String status, Long customerId, Long managerId, Long createdAfter, Pageable pageable) {
        log.info(LogMessage.IN_FILTER_ORDERS);
        return orderRepository.findAll(where(status == null ? null : OrderSpecification.hasStatus(status))
                        .and(customerId == null ? null : OrderSpecification.hasCustomer(customerId))
                        .and(managerId == null ? null : OrderSpecification.hasManager(managerId))
                        .and(createdAfter == null ? null : OrderSpecification.createdAfter(createdAfter)), pageable)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    public boolean isOrderBelongsToUser(Long orderId, Long userId) {
        log.info(LogMessage.IN_ORDER_BELONGS_TO_USER, orderId, userId);
        return orderRepository.existsByIdAndCustomerIdOrManagerId(orderId, userId, userId);
    }

    public OrderDto findOrderDto(Long orderId) {
        return modelMapper.map(findById(orderId), OrderDto.class);
    }

    public List<OrderDto> findOrderDtoByCustomer(Long customerId) {
        log.info(LogMessage.IN_FIND_ORDERS_BY_CUSTOMER, customerId);
        return modelMapper.map(orderRepository.findByCustomerId(customerId), new TypeToken<List<OrderDto>>() {
        }.getType());
    }

    public List<OrderDto> findOrderDtoByManager(Long managerId) {
        log.info(LogMessage.IN_FIND_ORDERS_BY_MANAGER, managerId);
        return modelMapper.map(orderRepository.findByManagerId(managerId), new TypeToken<List<OrderDto>>() {
        }.getType());
    }
}
