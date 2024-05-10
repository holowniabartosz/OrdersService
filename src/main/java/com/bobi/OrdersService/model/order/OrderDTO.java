package com.bobi.OrdersService.model.order;

import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private final String orderId = "order:" + UUID.randomUUID();
    private final Instant time = Instant.now();
    private final List<OrderedProduct> orderedProducts;
    private BillingData billingData;

    public OrderDTO(List<OrderedProduct> orderedProducts, BillingData billingData) {
        this.orderedProducts = orderedProducts;
        this.billingData = billingData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderDTO))
            return false;

        OrderDTO other = (OrderDTO) o;

        return orderId != null &&
                orderId.equals(other.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
