package com.manerajona.composition.rs.mapper;

import com.manerajona.composition.core.dto.OrderDto;
import com.manerajona.composition.rs.dto.OrderRequest;
import com.manerajona.composition.rs.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderControllerMapper {

    @Mapping(target = "items", source = "orderItems")
    OrderDto orderRequestToOrderDto(OrderRequest request);

    @Mapping(target = "orderItems", source = "items")
    OrderResponse orderDtoToOrderResponse(OrderDto dto);
}
