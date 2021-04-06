package com.dong.controller;

import com.dong.common.constant.StatusCode;
import com.dong.common.dto.ResultDto;
import com.dong.common.util.LogUtil;
import com.dong.common.util.ResponseUtil;
import com.dong.user.dto.UserDto;
import com.dong.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static Logger log = LogUtil.get(UserController.class);

    @DubboReference // 引入dubbo远程对象
    private UserService userService;

    @GetMapping("/users")
    public ResultDto<Object> getUsers() {
        List<UserDto> users = userService.getUsers();
        return ResponseUtil.ok(users);
    }

    @PostMapping("/addUser")
    public ResultDto<Object> addUser() {
        UserDto user = new UserDto();
        user.setName("Tom");
        user.setSex(1);
        user.setSexText("男");
        UserDto dto = userService.addUser(user);
        return ResponseUtil.ok(dto);
    }

    @PostMapping("/updateUser")
    public ResultDto<Object> updateUser() {
        UserDto user = new UserDto();
        user.setId(2);
        user.setName("Susan");
        user.setSex(2);
        user.setSexText("女");
        UserDto dto = userService.updateUser(user);
        return ResponseUtil.ok(dto);
    }

    @PostMapping("/deleteUser")
    public ResultDto<Object> deleteUser() {
        int count = userService.deleteUser(2);
        return ResponseUtil.ok(count);
    }

}
