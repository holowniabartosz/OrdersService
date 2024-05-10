package com.bobi.OrdersService.controller;

import com.bobi.OrdersService.model.order.OrderDTO;
import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder (@RequestBody BillingData billingData, @RequestParam("cartId") String cartId) throws JsonProcessingException {
        return orderService.createOrder(billingData, cartId);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder (@PathVariable String orderId) throws JsonProcessingException {
        return orderService.getOrder(orderId);
    }
}