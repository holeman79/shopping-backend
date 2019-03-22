package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PhoneFirstNumberType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private int numberOrder;
}
