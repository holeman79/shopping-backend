package com.holeman79.shoppingbackend.product.domain;

import com.holeman79.shoppingbackend.generic.code.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_IMAGE_GROUPS")
public class ProductImageGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_IMAGE_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_IMAGE_GROUP_ID")
    private List<ProductImage> productImages = new ArrayList();

    public void uploadImages(Category category, Long productId, String productName){
        for(ProductImage productImage : productImages){

        }
    }
}
