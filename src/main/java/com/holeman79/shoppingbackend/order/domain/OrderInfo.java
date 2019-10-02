package com.holeman79.shoppingbackend.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNo;

    private String ordererName;
    private String zipcode;
    private String address;
    private String detailAddress;
    private String mobileNo1;
    private String mobileNo2;
    private String mobileNo3;

    @ManyToOne
    @JoinColumn(name = "payment_code")
    private PaymentWay selectedPayment;
    private String depositorName;

    @ManyToOne
    @JoinColumn(name = "bankbook_code")
    private BankBook selectedBankBook;
    private int paymentCheck;

    private int totalPrice;

    private String userId;

    private String name;

    @OneToMany(mappedBy = "orderInfo", cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;

}
