package com.holeman79.shoppingbackend.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_product_file")
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String directory;
    private String originalFileName;
    private String savedFileName;
    private String thumbnailSavedFileName;
    private Long fileSize;
    private Date createdAt;
    private String createdId;

    @OneToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;

    @PrePersist
    public void beforeCreate(){
        createdAt = new Date();
    }
}
