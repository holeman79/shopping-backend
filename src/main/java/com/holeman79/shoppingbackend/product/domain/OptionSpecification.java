package com.holeman79.shoppingbackend.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_SPECS")
public class OptionSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;
}
