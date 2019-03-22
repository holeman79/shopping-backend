package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class BankBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String bankName;
    private String bankAccount;
    private String accountHolder;
}
