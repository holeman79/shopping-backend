package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tb_phone_first_number_type")
public class PhoneFirstNumberType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private int numberOrder;
}
