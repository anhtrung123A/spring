package com.trungpham.playaround.domains.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private ProductEntity product;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private OrderEntity order;
    @Column(name = "quantity", nullable = false)
    private int quantity;
}
