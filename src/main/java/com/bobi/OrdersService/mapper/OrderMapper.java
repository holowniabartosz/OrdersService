package com.bobi.OrdersService.mapper;

import com.bobi.OrdersService.model.order.Order;
import com.bobi.OrdersService.model.order.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDTO(Order order);

    Order toOrder(OrderDTO orderDTO);
}