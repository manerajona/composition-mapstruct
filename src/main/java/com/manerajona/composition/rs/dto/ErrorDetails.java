package com.manerajona.composition.rs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.manerajona.composition.rs.enums.ErrorLocation;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {
    @NotNull Integer code;
    @NotNull String detail;
    String field;
    Object value;
    ErrorLocation location;
}
