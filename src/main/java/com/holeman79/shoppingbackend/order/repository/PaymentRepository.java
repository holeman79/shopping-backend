package com.holeman79.shoppingbackend.order.repository;

import com.holeman79.shoppingbackend.order.domain.PaymentWay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentWay, Long> {
}
