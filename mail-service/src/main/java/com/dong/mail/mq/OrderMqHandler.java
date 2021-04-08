package com.dong.mail.mq;

import cn.hutool.core.date.DateUtil;
import com.dong.common.util.LogUtil;
import com.dong.common.util.RedisUtil;
import com.dong.mail.dao.MailDao;
import com.dong.mail.domain.MailEntity;
import com.dong.mail.dto.MailDto;
import com.dong.mail.util.SendMailTool;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * rocketmq 监听处理类
 *
 * @author xiedongxiao
 */

@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "${rocketmq.topic.mail}")
@Component
public class OrderMqHandler implements RocketMQListener<MailDto> {

    private static Logger log = LogUtil.get(OrderMqHandler.class);

    @Autowired
    private MailDao mailDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SendMailTool sendMailTool;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void onMessage(MailDto message) {
        log.info("消费消息: {}", message.toString());
        MailEntity entity = new MailEntity();
        BeanUtils.copyProperties(message, entity);
        entity.setResponseTime(DateUtil.date());
        mailDao.save(entity);
        boolean flag = sendMailTool.sendSimpleMail(message.getReceiver(), message.getTitle(), message.getContent());
        if (flag) {
            entity.setSendStatus(1);
        } else {
            entity.setSendStatus(2);
        }
    }

}
