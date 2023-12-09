package org.sda.order.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRead {
    private Long productId;
    private int quantity;
}
