package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
@Setter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> orderOptions;
}
