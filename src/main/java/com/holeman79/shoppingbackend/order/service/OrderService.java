package com.holeman79.shoppingbackend.order.service;

import com.holeman79.shoppingbackend.order.domain.Order;
import com.holeman79.shoppingbackend.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order addOrder(Order order){
        order.ordered();
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
