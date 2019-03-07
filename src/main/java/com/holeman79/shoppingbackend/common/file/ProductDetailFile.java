package com.holeman79.shoppingbackend.common.file;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProductDetailFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uploadPath;
    private String directory;
    private String originalFileName;
    private String savedFileName;
    private Long fileSize;
    private Date createdAt;
    private String createdId;

    @Column(name="product_id")
    private Long productId;

    @PrePersist
    public void beforeCreate(){
        createdAt = new Date();
    }
}
