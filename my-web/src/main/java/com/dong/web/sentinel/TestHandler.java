package com.dong.web.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dong.common.util.LogUtil;
import com.dong.web.controller.UserController;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestHandler {

    private static Logger log = LogUtil.get(TestHandler.class);

    @SentinelResource(value = "haha", blockHandler = "handleHahaException")
    public String haha() {
        return "haha";
    }

    public String handleHahaException(BlockException exception) {
        log.info(exception.toString());
        return "haha服务熔断降级...";
    }

}
