package com.bobi.OrdersService.model.order.orderedProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedProduct {
    private String jsonProduct;
    private int quantity = 1;

    public OrderedProduct(String jsonProduct) {
        this.jsonProduct = jsonProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderedProduct))
            return false;

        OrderedProduct other = (OrderedProduct) o;

        return jsonProduct != null &&
                jsonProduct.equals(other.jsonProduct);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
