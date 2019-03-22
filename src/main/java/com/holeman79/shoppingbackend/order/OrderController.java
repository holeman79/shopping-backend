package com.holeman79.shoppingbackend.order;

import com.holeman79.shoppingbackend.order.domain.BankBook;
import com.holeman79.shoppingbackend.order.domain.OrderInfo;
import com.holeman79.shoppingbackend.order.domain.PaymentWay;
import com.holeman79.shoppingbackend.order.domain.PhoneFirstNumberType;
import com.holeman79.shoppingbackend.order.repository.BankBookRepository;
import com.holeman79.shoppingbackend.order.repository.PaymentRepository;
import com.holeman79.shoppingbackend.order.repository.PhoneFirstNumberTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orderInfo")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BankBookRepository bankBookRepository;

    @Autowired
    PhoneFirstNumberTypeRepository phoneFirstNumberTypeRepository;
    @GetMapping("/option")
    public ResponseEntity<?> getOptionList(){
        Map result = new HashMap();
        List<PaymentWay> paymentWays = paymentRepository.findAll();
        List<BankBook> bankBooks = bankBookRepository.findAll();
        List<PhoneFirstNumberType> phoneFirstNumberTypes = phoneFirstNumberTypeRepository.findAll();

        result.put("paymentWays", paymentWays);
        result.put("bankBooks", bankBooks);
        result.put("phoneFirstNumberTypes", phoneFirstNumberTypes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderInfo orderInfo){


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
