package com.holeman79.shoppingbackend.order.domain;

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
    private OrderInfo orderInfo;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;
    private Color color;
    private Size size;
    private int number;

    private OrderStatus orderStatus;
}
