package com.holeman79.shoppingbackend.order;

import com.holeman79.shoppingbackend.common.domain.OrderStatus;
import com.holeman79.shoppingbackend.common.util.OrderNoGenerator;
import com.holeman79.shoppingbackend.order.domain.OrderInfo;
import com.holeman79.shoppingbackend.order.domain.OrderItem;
import com.holeman79.shoppingbackend.order.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    public OrderInfo addOrder(OrderInfo orderInfo){
        Long orderNo = OrderNoGenerator.nextOrderNo();
        orderInfo.setOrderNo(orderNo);
        for(OrderItem orderItem : orderInfo.getOrderItems()){
            orderItem.setOrderInfo(orderInfo);
            orderItem.setOrderStatus(OrderStatus.PREPARING_PRODUCT);
        }

        OrderInfo savedOrderInfo = orderInfoRepository.save(orderInfo);

        return savedOrderInfo;
    }
}
