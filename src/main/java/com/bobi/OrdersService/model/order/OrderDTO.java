package com.bobi.OrdersService.model.order;

import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    private Instant time;
    private List<OrderedProduct> orderedProducts;
    private BillingData billingData;

    public OrderDTO(String orderId, Instant time, List<OrderedProduct> orderedProducts, BillingData billingData) {
        this.orderId = orderId;
        this.time = time;
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
