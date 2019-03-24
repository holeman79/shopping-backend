package com.holeman79.shoppingbackend.order.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.holeman79.shoppingbackend.common.domain.OrderStatus;
import com.holeman79.shoppingbackend.product.domain.Color;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_no")
    @JsonIgnore
    private OrderInfo orderInfo;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="color_code")
    private Color color;

    @ManyToOne
    @JoinColumn(name="size_code")
    private Size size;
    private int number;

    private OrderStatus orderStatus;
}
