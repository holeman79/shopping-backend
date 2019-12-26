package com.holeman79.shoppingbackend.order.domain;

import com.holeman79.shoppingbackend.generic.code.BankBook;
import com.holeman79.shoppingbackend.generic.code.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
public class Order {
    public enum OrderStatus { ORDERED, PAYED, DELIVERING, DELIVERED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDERER_NAME")
    private String ordererName;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ZIP_CODE")
    private String zipcode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANKBOOK")
    private BankBook bankBook;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems;

    @Column(name="CREATED_DATE")
    private LocalDateTime createdDate;

    @PrePersist
    public void beforeCreate(){
        createdDate = LocalDateTime.now();
    }

    public void ordered() {
        this.orderStatus = OrderStatus.ORDERED;
    }

    public void payed() {
        this.orderStatus = OrderStatus.PAYED;
    }

    public void delivering() {
        this.orderStatus = OrderStatus.DELIVERING;
    }

    public void delivered() {
        this.orderStatus = OrderStatus.DELIVERED;
    }
}
