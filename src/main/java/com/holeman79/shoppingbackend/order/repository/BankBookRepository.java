package com.holeman79.shoppingbackend.order.repository;

import com.holeman79.shoppingbackend.order.domain.BankBook;
import com.holeman79.shoppingbackend.order.domain.PaymentWay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankBookRepository extends JpaRepository<BankBook, Long> {
}
