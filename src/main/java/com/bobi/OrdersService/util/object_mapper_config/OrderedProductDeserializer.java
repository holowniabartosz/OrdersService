package com.bobi.OrdersService.util.object_mapper_config;

import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class OrderedProductDeserializer extends StdDeserializer<OrderedProduct> {

    public OrderedProductDeserializer() {
        super(OrderedProduct.class);
    }

    @Override
    public OrderedProduct deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        OrderedProduct product = new OrderedProduct();

        product.setQuantity(node.path("quantity").asInt());
        product.setProductClass(node.path("productClass").asText());
        product.setId(node.path("id").asLong());
        product.setName(node.path("name").asText());
        product.setPrice(node.path("price").asDouble(0.0)); // Default to 0.0 if not present

        String productClass = node.path("productClass").asText();

        if ("COMPUTER".equals(productClass)) {
            product.setComputerBrand(node.path("computerBrand").asText());
            product.setComputerType(node.path("computerType").asText());
            handleComputerFields(node, product);
        } else if ("SMARTPHONE".equals(productClass)) {
            product.setSmartphoneBrand(node.path("smartphoneBrand").asText());
            product.setOs(node.path("os").asText());
            handleSmartphoneFields(node, product);
        } else {
            product.setElectronicsBrand(node.path("electronicsBrand").asText());
            product.setElectronicsType(node.path("electronicsType").asText());
        }
        return product;
    }

    private void handleComputerFields(JsonNode node, OrderedProduct product) {
        JsonNode ramNode = node.path("ramGB");
        if (!ramNode.isMissingNode()) {
            product.setRamId(ramNode.path("ramId").asLong());
            product.setRamPrice(ramNode.path("priceRam").asDouble());
        }

        JsonNode cpuNode = node.path("cpu");
        if (!cpuNode.isMissingNode()) {
            product.setCpuId(cpuNode.path("cpuId").asLong());
            product.setCpuPrice(cpuNode.path("priceCpu").asDouble());
        }
    }

    private void handleSmartphoneFields(JsonNode node, OrderedProduct product) {
        JsonNode batteryNode = node.path("battery");
        if (!batteryNode.isMissingNode()) {
            product.setBatteryId(batteryNode.path("batteryId").asLong());
            product.setBatteryPrice(batteryNode.path("priceBattery").asDouble());
        }

        JsonNode colourNode = node.path("colour");
        if (!colourNode.isMissingNode()) {
            product.setColourId(colourNode.path("colourId").asLong());
            product.setColourPrice(colourNode.path("priceColour").asDouble());
        }
    }
}

