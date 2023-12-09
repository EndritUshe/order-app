package org.sda.order.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "oa_product")
public class Product extends BaseEntity{
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Float price;
    @Column(name="description")
    private String description;

}
