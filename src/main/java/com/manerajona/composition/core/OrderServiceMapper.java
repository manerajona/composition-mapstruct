package com.manerajona.composition.core;

import com.manerajona.composition.core.dto.OrderDto;
import com.manerajona.composition.core.dto.OrderItemDto;
import com.manerajona.composition.jpa.entity.Order;
import com.manerajona.composition.jpa.entity.OrderItem;
import com.manerajona.composition.jpa.enums.OrderState;
import org.mapstruct.AfterMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;
import java.util.Set;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consumer.id", ignore = true)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "items", qualifiedByName = "orderItemDtoSetToOrderItemSet")
    Order orderDtoToOrder(OrderDto dto, OrderState state);

    @IterableMapping(qualifiedByName = "orderItemDtoToOrderItem")
    @Named("orderItemDtoSetToOrderItemSet")
    Set<OrderItem> orderItemDtoSetToOrderItemSet(Set<OrderItemDto> list);

    @Mapping(target = "id", ignore = true)
    @Named("orderItemDtoToOrderItem")
    OrderItem orderItemDtoToOrderItem(OrderItemDto dto);

    @AfterMapping
    default void setOrder(@MappingTarget Order order) {

        Optional.ofNullable(order.getConsumer())
                .ifPresent(it -> it.setOrder(order));

        Optional.ofNullable(order.getItems())
                .ifPresent(it -> it.forEach(item -> item.setOrder(order)));
    }

    OrderDto orderToOrderDto(Order entity);
}
