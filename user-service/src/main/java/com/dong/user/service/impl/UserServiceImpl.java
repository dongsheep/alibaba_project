package com.dong.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.dong.common.constant.StatusCode;
import com.dong.common.exception.BussinessException;
import com.dong.common.util.LogUtil;
import com.dong.common.util.RedisUtil;
import com.dong.user.domain.UserEntity;
import com.dong.user.dto.UserDto;
import com.dong.user.mapper.UserMapper;
import com.dong.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * dubbo服务处理类
 *
 * @author xiedongxiao
 */

@DubboService
public class UserServiceImpl implements UserService {

    private static Logger log = LogUtil.get(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = userMapper.getUsers();
        return users;
    }

    @Transactional
    @Override
    public UserDto addUser(UserDto dto) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        user.setUpdateTime(DateUtil.date());
        int count = userMapper.insert(user);
        if (count == 1) {
            return userMapper.findUserById(user.getId());
        } else {
            log.warn("addUser success zero...");
            throw new BussinessException(StatusCode.INTERNAL_ERROR);
        }
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto dto) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        user.setUpdateTime(DateUtil.date());
        int count = userMapper.updateByPrimaryKeySelective(user);
        // 报错
//        double x = 1 / 0;
        if (count == 1) {
            return userMapper.findUserById(user.getId());
        } else {
            log.warn("updUser success zero...");
            throw new BussinessException(StatusCode.INTERNAL_ERROR);
        }
    }

    @Transactional
    @Override
    public int deleteUser(Integer id) {
        int count = userMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public UserDto findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

}
