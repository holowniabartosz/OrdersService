package com.bobi.OrdersService.model.order.orderedProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {
    private int quantity = 1;
    private String productClass;
    private Long id;
    private String name;
    private double price;
    // computer
    private String computerBrand;
    private String computerType;
    private long ramId;
    private double ramPrice;
    private long cpuId;
    private double cpuPrice;
    //smartphone
    private String smartphoneBrand;
    private String os;
    private long batteryId;
    private double batteryPrice;
    private long colourId;
    private double colourPrice;
    // electronics
    private String electronicsBrand;
    private String electronicsType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderedProduct))
            return false;

        OrderedProduct other = (OrderedProduct) o;

        return name != null &&
                name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
