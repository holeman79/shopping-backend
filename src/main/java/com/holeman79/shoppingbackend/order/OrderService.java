package com.holeman79.shoppingbackend.order;

import com.holeman79.shoppingbackend.order.domain.Order;
import com.holeman79.shoppingbackend.order.domain.OrderItem;
import com.holeman79.shoppingbackend.order.repository.OrderRepository;
import com.holeman79.util.OrderNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order addOrder(Order order){
//        Long orderNo = OrderNoGenerator.nextOrderNo();
//        order.setOrderNo(orderNo);
//        for(OrderItem orderItem : order.getOrderItems()){
//            orderItem.setOrder(order);
//            orderItem.setOrderStatusType(OrderStatusType.PREPARING_PRODUCT);
//        }
//
//        Order savedOrder = orderRepository.save(order);
//        return savedOrder;
        return null;
    }
}
