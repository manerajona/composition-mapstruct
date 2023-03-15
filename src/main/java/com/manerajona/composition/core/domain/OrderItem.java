package com.manerajona.composition.core.domain;

import java.util.UUID;

public record OrderItem(UUID id, String name, Integer quantity) {
}
