package com.holeman79.shoppingbackend.product.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "OPTION_GROUP_SPECS")
public class OptionGroupSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OPTION_GROUP_SPEC_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name = "EXCLUSIVE")
    private boolean exclusive;

    @Column(name = "BASIC")
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPTION_GROUP_SPEC_ID")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

}