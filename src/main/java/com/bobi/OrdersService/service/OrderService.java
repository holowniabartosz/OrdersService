package com.bobi.OrdersService.service;

import com.bobi.OrdersService.model.order.OrderDTO;
import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    OrderDTO createOrder(BillingData billingData, String cartId) throws JsonProcessingException;

    OrderDTO getOrder(String orderId) throws JsonProcessingException;
}
