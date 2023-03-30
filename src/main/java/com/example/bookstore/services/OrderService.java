package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.order.OrderBookDto;
import com.example.bookstore.dto.order.OrderDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public OrderDto createOrder(List<OrderBookDto> orderBookDtos, User user) {
        log.info("Creating order for user: {}", user.getEmail());

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
        log.info("Find order by id: {}", id);

        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND_BY_ID + id)
        );
    }

    public void setOrderStatus(Long orderId, OrderStatus status, User user) {
        log.info("Set order status: {} for order: {}", status, orderId);

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

    public List<OrderDto> filterOrders(String status, Long customerId, Long managerId, Long createdAfter, Pageable pageable) {
        log.info("Find filtered orders by status: {}, userId: {}, createdAfter: {}", status, (customerId != null ? customerId : managerId), createdAfter);

        return orderRepository.findAll(where(status == null ? null : OrderSpecification.hasStatus(status))
                        .and(customerId == null ? null : OrderSpecification.hasCustomer(customerId))
                        .and(managerId == null ? null : OrderSpecification.hasManager(managerId))
                        .and(createdAfter == null ? null : OrderSpecification.createdAfter(createdAfter)), pageable)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    public boolean isOrderBelongsToUser(Long orderId, Long userId) {
        log.info("Check if order: {} belongs to user: {}", orderId, userId);

        return orderRepository.existsByIdAndCustomerIdOrManagerId(orderId, userId, userId);
    }

    public OrderDto findOrderDto(Long orderId) {
        log.info("Find order dto by id: {}", orderId);

        return modelMapper.map(findById(orderId), OrderDto.class);
    }
}
