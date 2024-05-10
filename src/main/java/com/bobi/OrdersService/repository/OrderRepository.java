package com.bobi.OrdersService.repository;

import com.bobi.OrdersService.model.order.Order;
import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    private final StringRedisTemplate template;
    private final ObjectMapper objectMapper;

    public OrderRepository(StringRedisTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // to handle Instant properly
    }

    public Order createOrder(BillingData billingData, List<OrderedProduct> products) throws JsonProcessingException {
        Order order = new Order(products, billingData);
        saveOrder(order);
        return order;
    }

    public void saveOrder (Order order) throws JsonProcessingException {
        String jsonOrder = objectMapper.writeValueAsString(order);
        template.opsForValue().set(order.getOrderId(),jsonOrder);
    }

    public Order getOrder(String orderId) throws JsonProcessingException {
        String order = template.opsForValue().get(orderId);
        return objectMapper.readValue(order, Order.class);
    }
}
