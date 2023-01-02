package com.manerajona.composition.core.dto;

import com.manerajona.composition.jpa.enums.OrderState;

import java.util.Set;
import java.util.UUID;

public record OrderDto(UUID id, ConsumerDto consumer, Set<OrderItemDto> items, OrderState state, String notes) {
}
