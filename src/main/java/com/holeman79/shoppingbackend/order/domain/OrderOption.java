package com.holeman79.shoppingbackend.order.domain;

import com.holeman79.shoppingbackend.generic.code.Color;
import com.holeman79.shoppingbackend.generic.code.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class OrderOption {
    @Enumerated(EnumType.STRING)
    @Column(name = "COLOR")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "SIZE")
    private Size size;

    @Column(name = "PRICE")
    private int price;
}
