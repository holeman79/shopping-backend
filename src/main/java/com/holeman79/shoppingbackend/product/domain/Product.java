package com.holeman79.shoppingbackend.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name="category_code")
    private Category category;
    private int price;
    private int savedMoneyRate;
    private String description;
    private Date createdAt;
    private String createdId;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
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
