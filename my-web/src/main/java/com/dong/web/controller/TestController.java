package com.dong.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dong.web.sentinel.TestHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试api接口")
@RefreshScope
@RestController
public class TestController {

    @Value("${default.name}")
    private String name;

    /**
     * 定义一个资源
     * 资源内部发生异常都是时候调用
     * blockHandler 发生BlockException执行的方法，捕获的是Sentinel定义的异常，返回值和参数和原方法一致，参数最后可以加上BlockException
     * fallback 发生Throwable执行的方法，返回值和参数和原方法一致，参数最后可以加上Throwable
     *
     * @return
     */
    @SentinelResource(value = "test", blockHandler = "blockHandler", blockHandlerClass = TestHandler.class,
            fallback = "fallback", fallbackClass = TestHandler.class)
    @ApiOperation(value = "发送解析文本", notes = "发送解析文本test", produces = "application/json")
    @GetMapping("/test")
    public String test() {
        System.err.println(name);
        return "Hello Spring Cloud Alibaba...";
    }

    /**
     * Sentinel异常
     *
     * @param exception
     * @return
     */
//    public String blockHandler(BlockException exception) {
//        return "Hello Spring Cloud Alibaba...blockHandler";
//    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
//    public String fallback(Throwable e) {
//        return "Hello Spring Cloud Alibaba...fallback";
//    }

}
