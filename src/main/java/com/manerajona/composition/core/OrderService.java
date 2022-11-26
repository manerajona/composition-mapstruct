package com.manerajona.composition.core;

import com.manerajona.composition.core.dto.OrderDto;

import java.util.UUID;

public interface OrderService {
    UUID createOrder(OrderDto order);

    OrderDto getOrder(UUID guid);
}
