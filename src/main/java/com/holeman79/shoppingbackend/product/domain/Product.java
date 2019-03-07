package com.holeman79.shoppingbackend.product.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.holeman79.shoppingbackend.common.file.ProductDetailFile;
import com.holeman79.shoppingbackend.common.file.ProductFile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private String price;
    private String savedMoneyRate;
    private String description;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<Option> options;

    @OneToOne(mappedBy = "product", cascade=CascadeType.ALL)
    private ProductFile productFile;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<ProductDetailFile> productDetailFiles;
}
