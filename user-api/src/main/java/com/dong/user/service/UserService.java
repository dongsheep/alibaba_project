package com.dong.user.service;

import com.dong.user.dto.UserDto;

import java.util.List;

/**
 * provider service interface
 *
 * @author xiedongxiao
 */

public interface UserService {

    List<UserDto> getUsers();

    UserDto addUser(UserDto user);

    UserDto updateUser(UserDto user);

    int deleteUser(Integer id);

    UserDto findUserById(Integer id);

}
