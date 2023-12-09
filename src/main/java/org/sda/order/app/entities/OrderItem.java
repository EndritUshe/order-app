package org.sda.order.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_price")
    private float productPrice;
    @Column(name = "quantity")
    private int quantity;
}
