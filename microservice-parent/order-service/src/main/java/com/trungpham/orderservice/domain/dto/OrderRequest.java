package com.trungpham.orderservice.domain.dto;

import com.trungpham.orderservice.domain.entity.OrderLineItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
