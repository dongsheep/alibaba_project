package com.dong.mail.service.impl;

import cn.hutool.core.date.DateUtil;
import com.dong.common.util.LogUtil;
import com.dong.common.util.RedisUtil;
import com.dong.mail.dao.MailDao;
import com.dong.mail.domain.MailEntity;
import com.dong.mail.dto.MailDto;
import com.dong.mail.service.MailService;
import com.dong.mail.util.SendMailTool;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * dubbo服务处理类
 *
 * @author xiedongxiao
 */

@DubboService
public class MailServiceImpl implements MailService {

    private static Logger log = LogUtil.get(MailServiceImpl.class);

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
    public void sendEmail(MailDto dto) {
        MailEntity entity = new MailEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setResponseTime(DateUtil.date());
        mailDao.save(entity);
//        double x = 1 / 0;
        boolean flag = sendMailTool.sendSimpleMail(dto.getReceiver(), dto.getTitle(), dto.getContent());
        if (flag) {
            entity.setSendStatus(1);
        } else {
            entity.setSendStatus(2);
        }
    }

}
