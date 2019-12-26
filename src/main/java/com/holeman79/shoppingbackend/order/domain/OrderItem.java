package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_ITEMS")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "PRODUCT_PRICE")
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ITEM_ID")
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList();

}
