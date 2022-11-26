package com.manerajona.composition.jpa.repository;

import com.manerajona.composition.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {
}
