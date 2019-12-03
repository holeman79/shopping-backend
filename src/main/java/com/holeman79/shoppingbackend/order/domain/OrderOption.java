package com.holeman79.shoppingbackend.order.domain;

import com.holeman79.shoppingbackend.generic.code.Color;
import com.holeman79.shoppingbackend.generic.code.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
@Setter
public class OrderOption {
    @Column(name = "COLOR")
    private Color color;

    @Column(name = "SIZE")
    private Size size;

    @Column(name = "PRICE")
    private int price;
}
