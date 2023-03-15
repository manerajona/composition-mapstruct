package com.manerajona.composition.core;

import com.manerajona.composition.core.domain.Order;
import com.manerajona.composition.core.domain.OrderState;
import com.manerajona.composition.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepository repository;
    private final OrderServiceMapper mapper;

    public UUID createOrder(Order order) {
        return repository
                .save(mapper.orderToOrderJpa(order, OrderState.APPROVAL_PENDING))
                .getId();
    }

    @Override
    public Order getOrder(UUID guid) {
        return repository
                .findById(guid)
                .map(mapper::orderJpaToOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
