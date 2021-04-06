package com.dong.user;

import com.dong.common.util.LogUtil;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUser {

    private static Logger log = LogUtil.get(TestUser.class);

    @Test
    public void testLog() {
        log.info("hello world...");
    }

}
