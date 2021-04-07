package com.dong.order.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.common.constant.StatusCode;
import com.dong.common.exception.BussinessException;
import com.dong.common.util.LogUtil;
import com.dong.common.util.RedisUtil;
import com.dong.order.domain.OrderEntity;
import com.dong.order.dto.OrderDto;
import com.dong.order.mapper.OrderMapper;
import com.dong.order.service.OrderService;
import com.dong.user.dto.UserDto;
import com.dong.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * dubbo服务处理类
 *
 * @author xiedongxiao
 */

@DubboService
public class OrderServiceImpl implements OrderService {

    private static Logger log = LogUtil.get(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtil redisUtil;

    @DubboReference // 引入dubbo远程对象
    private UserService userService;

    @Override
    public List<OrderDto> findOrderByUserId(Integer userId) {
        AbstractWrapper condition = new QueryWrapper();
        condition.eq("buyer_id", userId);
        List<OrderEntity> lst = orderMapper.selectList(condition);
        List<OrderDto> rec = new ArrayList<>();
        OrderDto dto = null;
        for (OrderEntity e : lst) {
            dto = new OrderDto();
            BeanUtils.copyProperties(e, dto);
            rec.add(dto);
        }
        return rec;
    }

    @Transactional
    @Override
    public OrderDto createOrder(OrderDto dto) {
        OrderEntity order = new OrderEntity();
        BeanUtils.copyProperties(dto, order);
        order.setCreateTime(DateUtil.date());
        int count = orderMapper.insert(order);
        if (count == 1) {
            OrderDto obj = orderMapper.findOrderById(order.getId());
            // 远程调用user-service
            UserDto user = userService.findUserById(dto.getBuyerId());
            userService.updateUser(user);
            obj.setBuyer(user.getName());
            return obj;
        } else {
            log.warn("createOrder success zero...");
            throw new BussinessException(StatusCode.INTERNAL_ERROR);
        }
    }

}
