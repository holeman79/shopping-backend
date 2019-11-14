package com.holeman79.shoppingbackend.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE_GROUPS")
public class ProductImageGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_IMAGE_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
