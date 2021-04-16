package com.dong.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dong.common.dto.ResultDto;
import com.dong.common.util.LogUtil;
import com.dong.common.util.ResponseUtil;
import com.dong.user.dto.UserDto;
import com.dong.user.service.UserService;
import com.dong.web.sentinel.TestHandler;
import com.dong.web.sentinel.UserHandler;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static Logger log = LogUtil.get(UserController.class);

    /**
     * 初始化流控规则和熔断规则
     * ps:因为我们没有接入 Sentinel Dashboard，所以得自己在代码里面设置好
     */
//    static {
//        // 初始化流控规则
//        final List<FlowRule> flowRules = new ArrayList<>();
//        final List<DegradeRule> degradeRules = new ArrayList<>();
//        // 限流规则
//        final FlowRule flowRule = new FlowRule();
//        flowRule.setResource(RESOURCE_NAME);
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // 1 QPS
//        flowRule.setCount(1);
//        flowRules.add(flowRule);
//        // 熔断规则
//        final DegradeRule degradeRule = new DegradeRule();
//        degradeRule.setResource(RESOURCE_NAME);
//        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
//        // 2个异常数
//        degradeRule.setCount(1);
//        // 时间窗口长度，单位为秒
//        degradeRule.setTimeWindow(5);
//        // 最小请求数
//        degradeRule.setMinRequestAmount(5);
//        // 熔断时长：当5秒内，10个请求里面出现2个异常，则进行熔断，熔断时长为10s
//        degradeRule.setStatIntervalMs(10000);
//        degradeRules.add(degradeRule);
//
//        FlowRuleManager.loadRules(flowRules);
//        DegradeRuleManager.loadRules(degradeRules);
//    }

    @DubboReference // 引入dubbo远程对象
    private UserService userService;

//    @Autowired
//    private TestHandler testHandler;

//    @SentinelResource(value = "users", blockHandler = "handleException", blockHandlerClass = UserHandler.class)
    @GetMapping("/users")
    public ResultDto<Object> getUsers() {
//        log.info(testHandler.haha());
        List<UserDto> users = userService.getUsers();
        return ResponseUtil.ok(users);
    }

    @PostMapping("/addUser")
    public ResultDto<Object> addUser() {
        UserDto user = new UserDto();
        user.setName("Tom");
        user.setSex(1);
        user.setSexText("男");
        user.setEmail("408515371@qq.com");
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
        user.setEmail("408515371@qq.com");
        UserDto dto = userService.updateUser(user);
        return ResponseUtil.ok(dto);
    }

    @PostMapping("/deleteUser")
    public ResultDto<Object> deleteUser() {
        int count = userService.deleteUser(2);
        return ResponseUtil.ok(count);
    }

}
