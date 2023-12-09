package org.sda.order.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "oa_order")
public class Order extends BaseEntity {
    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name="total")
    private float total;
}
