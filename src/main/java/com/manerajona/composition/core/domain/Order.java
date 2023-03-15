package com.manerajona.composition.core.domain;

import java.util.Set;
import java.util.UUID;

public record Order(UUID id, Consumer consumer, Set<OrderItem> items, OrderState state, String notes) {
}
