package com.bobi.OrdersService.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "shopping-cart-service-client",
        url = "${shopping-cart-service.url}",
        configuration = FeignConfig.class
)
public interface ShoppingCartServiceClient {
    @GetMapping("/cart/{cartId}")
    List<String> getCartContents(@PathVariable String cartId);
}