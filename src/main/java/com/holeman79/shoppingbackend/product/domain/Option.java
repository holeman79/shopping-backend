package com.holeman79.shoppingbackend.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Option{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name="size_code", referencedColumnName = "code")
    private Size size;
    @ManyToOne
    @JoinColumn(name="color_code", referencedColumnName = "code")
    private Color color;
    private int number;

//    @Transient
//    private int sizeOrder;

}