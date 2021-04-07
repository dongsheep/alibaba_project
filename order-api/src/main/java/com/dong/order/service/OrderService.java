package com.dong.order.service;

import com.dong.order.dto.OrderDto;

import java.util.List;

/**
 * provider service interface
 *
 * @author xiedongxiao
 */

public interface OrderService {

    List<OrderDto> findOrderByUserId(Integer userId);

    OrderDto createOrder(OrderDto dto);

}
