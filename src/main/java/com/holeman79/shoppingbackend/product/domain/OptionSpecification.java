package com.holeman79.shoppingbackend.product.domain;

import com.holeman79.shoppingbackend.generic.code.Color;
import com.holeman79.shoppingbackend.generic.code.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "OPTION_SPECS")
public class OptionSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_SPEC_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "COLOR")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "SIZE")
    private Size size;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;
}
