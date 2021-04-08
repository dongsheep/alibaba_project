package com.dong.order.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.common.constant.StatusCode;
import com.dong.common.exception.BussinessException;
import com.dong.common.util.LogUtil;
import com.dong.common.util.RedisUtil;
import com.dong.mail.dto.MailDto;
import com.dong.mail.service.MailService;
import com.dong.order.domain.OrderEntity;
import com.dong.order.dto.OrderDto;
import com.dong.order.mapper.OrderMapper;
import com.dong.order.service.OrderService;
import com.dong.user.dto.UserDto;
import com.dong.user.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @DubboReference(timeout = 1000) // 引入dubbo远程对象
    private UserService userService;

    @DubboReference(timeout = 1000)
    private MailService mailService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic.mail}")
    private String topicMail;

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

    @GlobalTransactional
//    @Transactional
    @Override
    public OrderDto createOrder(OrderDto dto) {
        long st = System.currentTimeMillis();
        OrderEntity order = new OrderEntity();
        BeanUtils.copyProperties(dto, order);
        order.setCreateTime(DateUtil.date());
        int count = orderMapper.insert(order);
        if (count == 1) {
            OrderDto obj = orderMapper.findOrderById(order.getId());
            long orderEd = System.currentTimeMillis();
            log.info("order used:" + (orderEd - st) + "ms");
            // 远程调用user-service
            UserDto user = userService.findUserById(dto.getBuyerId());
            obj.setBuyer(user.getName());
            userService.updateUser(user);
            long userEd = System.currentTimeMillis();
            log.info("user used:" + (userEd - orderEd) + "ms");
            // 远程调用mail-service
            MailDto mail = new MailDto();
            mail.setModule("order");
            mail.setTitle("测试邮件");
            mail.setContent("hello world...");
            mail.setSendStatus(0);
            mail.setReceiver(user.getEmail());
//            mailService.sendEmail(mail);
            rocketMQTemplate.convertAndSend(topicMail, mail);
            long mailEd = System.currentTimeMillis();
            log.info("mail used:" + (mailEd - userEd) + "ms");
            return obj;
        } else {
            log.warn("createOrder success zero...");
            throw new BussinessException(StatusCode.INTERNAL_ERROR);
        }
    }

}
