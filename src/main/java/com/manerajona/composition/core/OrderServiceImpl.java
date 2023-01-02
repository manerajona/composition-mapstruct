package com.manerajona.composition.core;

import com.manerajona.composition.core.dto.OrderDto;
import com.manerajona.composition.jpa.enums.OrderState;
import com.manerajona.composition.jpa.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo repository;
    private final OrderServiceMapper mapper;

    public UUID createOrder(OrderDto dto) {
        return repository
                .save(mapper.orderDtoToOrder(dto, OrderState.APPROVAL_PENDING))
                .getId();
    }

    @Override
    public OrderDto getOrder(UUID guid) {
        return repository
                .findById(guid)
                .map(mapper::orderToOrderDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
