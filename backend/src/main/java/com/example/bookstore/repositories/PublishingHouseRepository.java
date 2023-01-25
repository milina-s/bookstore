package com.example.bookstore.repositories;

import com.example.bookstore.entities.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
}
