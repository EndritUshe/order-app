package org.sda.order.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_price")
    private float productPrice;
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
}
