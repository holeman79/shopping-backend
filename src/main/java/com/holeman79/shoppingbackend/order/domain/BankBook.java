package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_bank_book")
public class BankBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String bankName;
    private String bankAccount;
    private String accountHolder;
}
