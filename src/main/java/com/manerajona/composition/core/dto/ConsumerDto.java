package com.manerajona.composition.core.dto;

import java.util.UUID;

public record ConsumerDto(UUID id, String name, String address, String phone) {
}
