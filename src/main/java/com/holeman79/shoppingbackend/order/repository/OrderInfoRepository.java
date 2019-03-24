package com.holeman79.shoppingbackend.order.repository;

import com.holeman79.shoppingbackend.order.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
