package com.dong.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

@AutoConfigureMockMvc
@SpringBootTest
public class MockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sayHello() throws Exception {
//        for (int i = 0; i < 100; i++) {
//            // 休眠500毫秒，即1秒两次调用，可以出发流控规则
//            Thread.sleep(500);
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")).andReturn();
//            System.out.println(result.getResponse().getContentAsString());
//        }

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")).andReturn();
        MockHttpServletResponse response = result.getResponse();
//        System.err.println(new String(response.getContentAsByteArray(), "UTF-8"));
        System.err.println(response.getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void createOrder() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/createOrder")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.err.println(response.getContentAsString(Charset.defaultCharset()));
    }

}
