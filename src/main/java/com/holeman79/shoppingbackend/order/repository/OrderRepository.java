package com.holeman79.shoppingbackend.order.repository;

import com.holeman79.shoppingbackend.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
