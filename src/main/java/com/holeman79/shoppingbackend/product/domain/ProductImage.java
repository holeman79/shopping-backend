package com.holeman79.shoppingbackend.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_IMAGES")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_IMAGE_ID")
    private Long id;

    @Column(name = "IMAGE_NAME")
    private String name;

    @Column(name = "IMAGE_SIZE")
    private Long size;
}
