package com.dong.user.mapper;

import java.util.List;

import com.dong.common.mapper.BaseMapper;
import com.dong.user.domain.UserEntity;
import com.dong.user.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public interface UserMapper extends BaseMapper<UserEntity> {

	List<UserDto> getUsers();
	
	UserDto findUserById(Integer id);
    
}