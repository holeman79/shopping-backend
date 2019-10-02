package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tb_payment_way")
public class PaymentWay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String name;
}
