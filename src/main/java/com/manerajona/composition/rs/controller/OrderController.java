package com.manerajona.composition.rs.controller;

import com.manerajona.composition.core.OrderService;
import com.manerajona.composition.core.domain.Order;
import com.manerajona.composition.rs.api.OrdersApi;
import com.manerajona.composition.rs.dto.OrderRequest;
import com.manerajona.composition.rs.dto.OrderResponse;
import com.manerajona.composition.rs.mapper.OrderControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final OrderService service;
    private final OrderControllerMapper mapper;

    @Override
    public ResponseEntity<Void> createOrder(OrderRequest orderRequest) {
        final UUID id = service.createOrder(
                mapper.orderRequestToOrder(orderRequest)
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(String id) {
        Order order = service.getOrder(
                UUID.fromString(id)
        );
        return ResponseEntity.ok(
                mapper.orderToOrderResponse(order)
        );
    }
}
