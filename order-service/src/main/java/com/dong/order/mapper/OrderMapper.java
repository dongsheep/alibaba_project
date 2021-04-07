package com.dong.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.order.domain.OrderEntity;
import com.dong.order.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper extends BaseMapper<OrderEntity> {

    OrderDto findOrderById(Integer id);

}