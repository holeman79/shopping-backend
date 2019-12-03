package com.holeman79.shoppingbackend.order.service;

import com.holeman79.shoppingbackend.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        Order savedOrder = orderService.addOrder(order);
        if(savedOrder != null) return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
