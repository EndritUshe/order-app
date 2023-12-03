package org.sda.order.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer productId;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Float price;
    @Column(name="description")
    private String description;



}
