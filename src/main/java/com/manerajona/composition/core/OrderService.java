package com.manerajona.composition.core;

import com.manerajona.composition.core.domain.Order;

import java.util.UUID;

public interface OrderService {
    UUID createOrder(Order order);

    Order getOrder(UUID guid);
}
