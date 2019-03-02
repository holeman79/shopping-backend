package com.holeman79.shoppingbackend.product.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String price;
    private String savedMoneyRate;
    @OneToMany(mappedBy = "product")
    private List<Option> options;
    @OneToMany(mappedBy = "product")
    private List<ProductFile> productFiles;
}
