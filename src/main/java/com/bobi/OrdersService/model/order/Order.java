package com.bobi.OrdersService.model.order;

import com.bobi.OrdersService.model.order.billing_data.BillingData;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
    private String orderId;
    private Instant time;
    private List<OrderedProduct> orderedProducts;
    private BillingData billingData;

    public Order(List<OrderedProduct> orderedProducts, BillingData billingData) {
        this.orderId = "order:" + UUID.randomUUID();
        this.time = Instant.now();
        this.orderedProducts = orderedProducts;
        this.billingData = billingData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Order))
            return false;

        Order other = (Order) o;

        return orderId != null &&
                orderId.equals(other.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
