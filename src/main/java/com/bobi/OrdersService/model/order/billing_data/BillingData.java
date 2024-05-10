package com.bobi.OrdersService.model.order.billing_data;

import lombok.Data;

@Data
public class BillingData {
    private String name;
    private String surname;
    private String street;
    private String streetNr;
    private String apartmentNr;
    private String zip;
    private String city;
    private String country;
    private String taxId;
}
