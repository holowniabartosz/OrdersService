package com.bobi.OrdersService.service;

import com.bobi.OrdersService.mapper.OrderMapper;
import com.bobi.OrdersService.model.order.OrderDTO;
import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.bobi.OrdersService.remote.ShoppingCartServiceClient;
import com.bobi.OrdersService.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(BillingData billingData, String cartId) throws JsonProcessingException {
        Map<String, OrderedProduct> productMap = new HashMap<>();
        List<String> cartProducts = shoppingCartServiceClient.getCartContents(cartId);
        for (String cartProduct : cartProducts) {
            productMap.compute(cartProduct, (key, value) -> {
                if (value == null) {
                    return new OrderedProduct(cartProduct);
                } else {
                    value.setQuantity(value.getQuantity() + 1);
                    return value;
                }
            });
        }
        List<OrderedProduct> orderedProducts = new ArrayList<>(productMap.values());
        return orderMapper.toDTO(orderRepository.createOrder(billingData, orderedProducts));
    }

    @Override
    public OrderDTO getOrder(String orderId) throws JsonProcessingException {
        return orderMapper.toDTO(orderRepository.getOrder(orderId));
    }
}
