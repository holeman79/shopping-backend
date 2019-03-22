package com.holeman79.shoppingbackend.order.domain;

import com.holeman79.shoppingbackend.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    private String selectedPayment;
    private String depositorName;
    private String bankBook;
    private int paymentCheck;

    private int totalPrice;

    private String username;

    @OneToMany(mappedBy = "orderInfo", cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;

}
