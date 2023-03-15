package com.manerajona.composition.core;

import com.manerajona.composition.core.domain.Order;
import com.manerajona.composition.core.domain.OrderItem;
import com.manerajona.composition.core.domain.OrderState;
import com.manerajona.composition.jpa.entity.OrderItemJpaEntity;
import com.manerajona.composition.jpa.entity.OrderJpaEntity;
import org.mapstruct.*;

import java.util.Optional;
import java.util.Set;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consumer.id", ignore = true)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "items", qualifiedByName = "orderItemSetToOrderItemJpaSet")
    OrderJpaEntity orderToOrderJpa(Order order, OrderState state);

    @IterableMapping(qualifiedByName = "orderItemToOrderJpaItem")
    @Named("orderItemSetToOrderItemJpaSet")
    Set<OrderItemJpaEntity> orderItemSetToOrderItemJpaSet(Set<OrderItem> list);

    @Mapping(target = "id", ignore = true)
    @Named("orderItemToOrderJpaItem")
    OrderItemJpaEntity orderItemToOrderJpaItem(OrderItem item);

    @AfterMapping
    default void setOrder(@MappingTarget OrderJpaEntity order) {

        Optional.ofNullable(order.getConsumer())
                .ifPresent(it -> it.setOrder(order));

        Optional.ofNullable(order.getItems())
                .ifPresent(it -> it.forEach(item -> item.setOrder(order)));
    }

    Order orderJpaToOrder(OrderJpaEntity entity);
}
