package com.geek.myredis7studing.controller;

import com.geek.myredis7studing.redis.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {


    @Autowired
    OrderService orderService;
    @PostMapping("/Orders")
    public ResponseEntity<Object> addOrder(){
        orderService.addOrder();
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/Orders/{keyId}")
    public ResponseEntity<Object> queryOrder(@PathVariable Integer keyId){

        String orderId = orderService.getOrderId(keyId);

        return ResponseEntity.ok(orderId);
    }
}
