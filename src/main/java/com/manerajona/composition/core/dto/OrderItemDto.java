package com.manerajona.composition.core.dto;

import java.util.UUID;

public record OrderItemDto(UUID id, String name, Integer quantity) {
}
