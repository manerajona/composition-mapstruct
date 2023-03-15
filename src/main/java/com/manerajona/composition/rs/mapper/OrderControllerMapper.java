package com.manerajona.composition.rs.mapper;

import com.manerajona.composition.core.domain.Order;
import com.manerajona.composition.rs.dto.OrderRequest;
import com.manerajona.composition.rs.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderControllerMapper {

    @Mapping(target = "items", source = "orderItems")
    Order orderRequestToOrder(OrderRequest request);

    @Mapping(target = "orderItems", source = "items")
    OrderResponse orderToOrderResponse(Order order);
}
