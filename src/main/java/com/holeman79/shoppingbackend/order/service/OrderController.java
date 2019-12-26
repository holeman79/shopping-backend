package com.holeman79.shoppingbackend.order.service;

import com.holeman79.shoppingbackend.generic.code.convert.EnumMapper;
import com.holeman79.shoppingbackend.generic.code.convert.EnumMapperValue;
import com.holeman79.shoppingbackend.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final EnumMapper enumMapper;

    @GetMapping("/options")
    public ResponseEntity<Map<String, List<EnumMapperValue>>> getOrderOptions(){
        Map<String, List<EnumMapperValue>> options = new LinkedHashMap();
        options.put("phoneFirstNumbers", enumMapper.get("phoneFirstNumbers"));
        options.put("paymentTypes", enumMapper.get("paymentTypes"));
        options.put("bankBooks", enumMapper.get("bankBooks"));

        return ResponseEntity.status(HttpStatus.OK).body(options);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order savedOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(savedOrder);
    }
}
