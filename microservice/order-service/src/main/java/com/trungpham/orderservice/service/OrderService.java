package com.trungpham.orderservice.service;

import com.trungpham.orderservice.domain.dto.InventoryResponse;
import com.trungpham.orderservice.domain.dto.OrderLineItemsDto;
import com.trungpham.orderservice.domain.dto.OrderRequest;
import com.trungpham.orderservice.domain.entity.Order;
import com.trungpham.orderservice.domain.entity.OrderLineItems;
import com.trungpham.orderservice.event.OrderPlaceEvent;
import com.trungpham.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private final KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(this::mapToOrderLineItems).toList())
                .build();
        // Call Inventory Service, and place order if product is in
        // stock
        List<String> skuCodes =  order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder ->
                        uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        log.info(String.valueOf(inventoryResponses.length == skuCodes.size()));
        if(allProductInStock && inventoryResponses.length == skuCodes.size()){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlaceEvent(order.getOrderNumber()));
            return "Order placed successfully";
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }
    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
