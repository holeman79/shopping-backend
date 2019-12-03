package com.holeman79.shoppingbackend.product.domain;

import com.holeman79.shoppingbackend.generic.code.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private Long id;

    @Column(name="PRODUCT_NAME")
    private String name;

    @Column(name="CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name="PRICE")
    private int price;

    @Column(name="SAVED_MONEY_RATE")
    private int savedMoneyRate;

    @Column(name="PRODUCT_DESCRIPTION")
    private String description;

    @Column(name="CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name="CREATOR_ID")
    private Long userId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList();

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private List<ProductImageGroup> productImageGroups = new ArrayList();

    @PrePersist
    public void beforeCreate(){
        createdDate = LocalDateTime.now();
    }

    public void setSavedUri(){
        for(ProductImageGroup productImageGroup : productImageGroups)
            productImageGroup.setSavedUri(category, id, name);
    }

    public void setValue(){
        for(OptionGroupSpecification optionGroupSpecification : optionGroupSpecs)
            optionGroupSpecification.setValue();
    }
}
