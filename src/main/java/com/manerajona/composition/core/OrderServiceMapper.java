package com.manerajona.composition.core;

import com.manerajona.composition.core.dto.OrderDto;
import com.manerajona.composition.core.dto.OrderItemDto;
import com.manerajona.composition.jpa.entity.Order;
import com.manerajona.composition.jpa.entity.OrderItem;
import com.manerajona.composition.jpa.enums.OrderState;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface OrderServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consumer.id", ignore = true)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "items", qualifiedByName = "orderItemDtoToOrderItem")
    Order orderDtoToOrder(OrderDto dto, OrderState state);

    @Mapping(target = "id", ignore = true)
    @Named("orderItemDtoToOrderItem")
    OrderItem orderItemDtoToOrderItem(OrderItemDto dto);

    OrderDto orderToOrderDto(Order entity);
}
