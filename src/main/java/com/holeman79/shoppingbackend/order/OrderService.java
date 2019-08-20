package com.holeman79.shoppingbackend.order;

import com.holeman79.shoppingbackend.order.domain.OrderInfo;
import com.holeman79.shoppingbackend.order.domain.OrderItem;
import com.holeman79.shoppingbackend.order.domain.enums.OrderStatusType;
import com.holeman79.shoppingbackend.order.repository.OrderInfoRepository;
import com.holeman79.util.OrderNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    public OrderInfo addOrder(OrderInfo orderInfo){
        Long orderNo = OrderNoGenerator.nextOrderNo();
        orderInfo.setOrderNo(orderNo);
        for(OrderItem orderItem : orderInfo.getOrderItems()){
            orderItem.setOrderInfo(orderInfo);
            orderItem.setOrderStatusType(OrderStatusType.PREPARING_PRODUCT);
        }

        OrderInfo savedOrderInfo = orderInfoRepository.save(orderInfo);
        return savedOrderInfo;
    }
}
