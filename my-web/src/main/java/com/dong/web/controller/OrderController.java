package com.dong.web.controller;

import com.dong.common.dto.ResultDto;
import com.dong.common.util.LogUtil;
import com.dong.common.util.ResponseUtil;
import com.dong.order.dto.OrderDto;
import com.dong.order.service.OrderService;
import com.dong.user.dto.UserDto;
import com.dong.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderController {

    private static Logger log = LogUtil.get(OrderController.class);

    @DubboReference // 引入dubbo远程对象
    private OrderService orderService;

    @GetMapping("/orders/{userId}")
    public ResultDto<Object> getOrders(@PathVariable Integer userId) {
        List<OrderDto> lst = orderService.findOrderByUserId(userId);
        return ResponseUtil.ok(lst);
    }

    @PostMapping("/createOrder")
    public ResultDto<Object> createOrder() {
        OrderDto order = new OrderDto();
        order.setOrderCode("123456789");
        order.setBuyerId(1);
        order.setTotalAmount(new BigDecimal("5000"));
        OrderDto dto = orderService.createOrder(order);
        return ResponseUtil.ok(dto);
    }

}
