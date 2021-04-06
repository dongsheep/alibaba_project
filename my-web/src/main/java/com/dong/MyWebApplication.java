package com.dong;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDubbo // 此注解为了自动开启dubbo
@EnableDiscoveryClient
@SpringBootApplication
public class MyWebApplication {

    public static void main(String[] args) {
        // 开启log4j2全异步，减小输出日志对性能的影响
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(MyWebApplication.class, args);
    }

}
