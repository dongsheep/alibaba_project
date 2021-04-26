package com.dong;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableDubbo // 此注解为了自动开启dubbo
@EnableDiscoveryClient
@SpringBootApplication
public class MyWebApplication {

    public static void main(String[] args) {
        // 开启log4j2全异步，减小输出日志对性能的影响
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(MyWebApplication.class, args);
    }

    /**
     * 需要引入一下，因为 Sentile 提供的这个切面是没有加 @Component 注解的
     *
     * @return
     */
//    @Bean
//    public SentinelResourceAspect sentinelResourceAspect() {
//        return new SentinelResourceAspect();
//    }

}
