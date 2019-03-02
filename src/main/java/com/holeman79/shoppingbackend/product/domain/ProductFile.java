package com.holeman79.shoppingbackend.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private int fileSize;
    private Date createdAt;
    private String createdId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;

    @PrePersist
    public void beforeCreate(){
        createdAt = new Date();
    }
}
