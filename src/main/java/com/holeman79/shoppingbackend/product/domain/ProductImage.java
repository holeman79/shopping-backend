package com.holeman79.shoppingbackend.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.holeman79.shoppingbackend.file.UploadFileUtils;
import com.holeman79.shoppingbackend.generic.code.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
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

    @Transient
    private MultipartFile image;

    @Transient
    private String savedUri;

    public void setSavedURI(Category category, Long productId, String productName, String groupName){
        String savedUri = UploadFileUtils.makeStringPath(category.getKey(), productId + "." + productName, groupName, name);
        this.savedUri = savedUri;
    }
}
