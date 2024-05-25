package com.trungpham.orderservice.service;

import com.trungpham.orderservice.domain.dto.OrderLineItemsDto;
import com.trungpham.orderservice.domain.dto.OrderRequest;
import com.trungpham.orderservice.domain.entity.Order;
import com.trungpham.orderservice.domain.entity.OrderLineItems;
import com.trungpham.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(this::mapToOrderLineItems).toList())
                .build();
        orderRepository.save(order);
    }
    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
