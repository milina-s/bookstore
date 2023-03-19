package com.example.bookstore.model.order;

import com.example.bookstore.model.user.UserRole;

public enum OrderStatus {

    CREATED(UserRole.ROLE_CUSTOMER),
    PAID(UserRole.ROLE_CUSTOMER),
    CONFIRMED(UserRole.ROLE_MANAGER),
    IN_PROGRESS(UserRole.ROLE_MANAGER),
    COMPLETED(UserRole.ROLE_MANAGER),
    CANCELED(UserRole.ROLE_CUSTOMER),
    DECLINED(UserRole.ROLE_MANAGER);

    private final UserRole role;

    OrderStatus(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

}
