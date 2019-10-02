package com.holeman79.shoppingbackend.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_color")
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String name;
}
