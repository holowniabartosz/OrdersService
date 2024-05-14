package com.bobi.OrdersService.util.object_mapper_config;

import com.bobi.OrdersService.model.order.Order;
import com.bobi.OrdersService.model.order.orderedProduct.OrderedProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomDeserializer");
        module.addDeserializer(OrderedProduct.class, new OrderedProductDeserializer());
        module.addSerializer(OrderedProduct.class, new OrderedProductSerializer());
        module.addSerializer(Order.class, new OrderSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}