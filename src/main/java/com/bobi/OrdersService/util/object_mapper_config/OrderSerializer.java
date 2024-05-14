package com.bobi.OrdersService.util.object_mapper_config;

import com.bobi.OrdersService.model.order.Order;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        super(Order.class);
    }

    @Override
    public void serialize(Order order, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("orderId", order.getOrderId());
        gen.writeStringField("time", order.getTime().toString());
        gen.writeArrayFieldStart("orderedProducts");
        for (OrderedProduct product : order.getOrderedProducts()) {
            provider.defaultSerializeValue(product, gen);  // Utilizing the default serializer or custom if configured
        }
        gen.writeEndArray();
        gen.writeObjectFieldStart("billingData");
        gen.writeStringField("name", order.getBillingData().getName());
        gen.writeStringField("surname", order.getBillingData().getSurname());
        gen.writeStringField("street", order.getBillingData().getStreet());
        gen.writeStringField("streetNr", order.getBillingData().getStreetNr());
        gen.writeStringField("apartmentNr", order.getBillingData().getApartmentNr());
        gen.writeStringField("zip", order.getBillingData().getZip());
        gen.writeStringField("city", order.getBillingData().getCity());
        gen.writeStringField("country", order.getBillingData().getCountry());
        gen.writeStringField("taxId", order.getBillingData().getTaxId());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}

class OrderedProductSerializer extends StdSerializer<OrderedProduct> {

    public OrderedProductSerializer() {
        super(OrderedProduct.class);
    }

    @Override
    public void serialize(OrderedProduct product, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("quantity", product.getQuantity());
        gen.writeNumberField("id", product.getId());
        gen.writeStringField("name", product.getName());
        gen.writeNumberField("price", product.getPrice());
        gen.writeStringField("productClass", product.getProductClass());

        if ("COMPUTER".equals(product.getProductClass())) {
            gen.writeStringField("computerBrand", product.getComputerBrand());
            gen.writeStringField("computerType", product.getComputerType());
            gen.writeNumberField("ramId", product.getRamId());
            gen.writeNumberField("ramPrice", product.getRamPrice());
            gen.writeNumberField("cpuId", product.getCpuId());
            gen.writeNumberField("cpuPrice", product.getCpuPrice());
        } else if ("SMARTPHONE".equals(product.getProductClass())) {
            gen.writeStringField("smartphoneBrand", product.getSmartphoneBrand());
            gen.writeStringField("os", product.getOs());
            gen.writeNumberField("batteryId", product.getBatteryId());
            gen.writeNumberField("batteryPrice", product.getBatteryPrice());
            gen.writeNumberField("colourId", product.getColourId());
            gen.writeNumberField("colourPrice", product.getColourPrice());
        } else {
            gen.writeStringField("electronicsBrand", product.getElectronicsBrand());
            gen.writeStringField("electronicsType", product.getElectronicsType());
        }
        gen.writeEndObject();
    }
}