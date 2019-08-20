package com.holeman79.shoppingbackend.order;

import com.holeman79.shoppingbackend.order.domain.BankBook;
import com.holeman79.shoppingbackend.order.domain.OrderInfo;
import com.holeman79.shoppingbackend.order.domain.PaymentWay;
import com.holeman79.shoppingbackend.order.domain.PhoneFirstNumberType;
import com.holeman79.shoppingbackend.order.repository.BankBookRepository;
import com.holeman79.shoppingbackend.order.repository.PaymentRepository;
import com.holeman79.shoppingbackend.order.repository.PhoneFirstNumberTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    private final PaymentRepository paymentRepository;

    private final BankBookRepository bankBookRepository;

    private final PhoneFirstNumberTypeRepository phoneFirstNumberTypeRepository;

    public OrderController(OrderService orderService, PaymentRepository paymentRepository,
                           BankBookRepository bankBookRepository, PhoneFirstNumberTypeRepository phoneFirstNumberTypeRepository){
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
        this.bankBookRepository = bankBookRepository;
        this.phoneFirstNumberTypeRepository = phoneFirstNumberTypeRepository;
    }
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
        OrderInfo savedOrderInfo = orderService.addOrder(orderInfo);
        if(savedOrderInfo != null) return new ResponseEntity<OrderInfo>(savedOrderInfo, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
