package com.manerajona.composition.core;

import com.manerajona.composition.core.domain.Order;
import com.manerajona.composition.core.domain.OrderItem;
import com.manerajona.composition.core.domain.OrderState;
import com.manerajona.composition.jpa.entity.OrderItemJpaEntity;
import com.manerajona.composition.jpa.entity.OrderJpaEntity;
import org.mapstruct.*;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface OrderServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consumer.id", ignore = true)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "items", qualifiedByName = "orderItemToOrderItemJpa")
    OrderJpaEntity orderToOrderJpa(Order order, OrderState state);

    @Mapping(target = "id", ignore = true)
    @Named("orderItemToOrderItemJpa")
    OrderItemJpaEntity orderItemToOrderItemJpa(OrderItem item);

    Order orderJpaToOrder(OrderJpaEntity entity);
}
