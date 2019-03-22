package com.holeman79.shoppingbackend.product.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToOne
    @JoinColumn(name="category_code", referencedColumnName = "code")
    private Category category;
    private String price;
    private String savedMoneyRate;
    private String description;
    private Date createdAt;
    private String createdId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<Option> options;

    @OneToOne(mappedBy = "product", cascade=CascadeType.ALL)
    private ProductFile productFile;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    private List<ProductDetailFile> productDetailFiles;

    @PrePersist
    public void beforeCreate(){
        createdAt = new Date();
    }
}
