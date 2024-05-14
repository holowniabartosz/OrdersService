package com.bobi.OrdersService.service;

import com.bobi.OrdersService.mapper.OrderMapper;
import com.bobi.OrdersService.model.order.Order;
import com.bobi.OrdersService.model.order.OrderDTO;
import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.bobi.OrdersService.remote.ShoppingCartServiceClient;
import com.bobi.OrdersService.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ShoppingCartServiceClient shoppingCartServiceClient;
    private OrderMapper orderMapper;
    private ObjectMapper objectMapper;

    @Override
    public OrderDTO createOrder(BillingData billingData, String cartId) throws JsonProcessingException {
        Map<String, OrderedProduct> productMap = new HashMap<>();
        List<String> cartProducts = shoppingCartServiceClient.getCartContents(cartId);
        for (String cartProduct : cartProducts) {
            productMap.compute(cartProduct, (key, value) -> {
                if (value == null) {
                    try {
                        return objectMapper.readValue(cartProduct, OrderedProduct.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    value.setQuantity(value.getQuantity() + 1);
                    return value;
                }
            });
        }
        List<OrderedProduct> orderedProducts = new ArrayList<>(productMap.values());
        Order newOrder = orderRepository.createOrder(billingData, orderedProducts);
        OrderDTO returnorder = orderMapper.toDTO(newOrder);
        System.out.println(returnorder + "!!!!!!!!!after toDTO mapping");
        // return orderMapper.toDTO(newOrder);
        return returnorder;
    }

    @Override
    public OrderDTO getOrder(String orderId) throws JsonProcessingException {
        Order order = orderRepository.getOrder(orderId);
        System.out.println("+++++++++"+order+"++++++++++");
        return orderMapper.toDTO(order);
    }
}
