package com.bobi.OrdersService.model.order.orderedProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductAllTypeFieldsDTO {
    private int quantity;
    private String productClass;
    private Long id;
    private String name;
    private double price;
    // electronics
    private String electronicsBrand;
    private String electronicsType;
    // computer
    private String computerBrand;
    private String computerType;
    private String ramGB;
    private String cpu;
    //smartphone
    private String smartphoneBrand;
    private String os;
    private String battery;
    private String colour;
}
