package com.holeman79.shoppingbackend.order.repository;

import com.holeman79.shoppingbackend.order.domain.PaymentWay;
import com.holeman79.shoppingbackend.order.domain.PhoneFirstNumberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneFirstNumberTypeRepository extends JpaRepository<PhoneFirstNumberType, Long> {
}
