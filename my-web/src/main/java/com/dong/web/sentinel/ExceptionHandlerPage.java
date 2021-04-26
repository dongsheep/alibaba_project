package com.dong.web.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import com.dong.common.dto.ResultDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentinel自定义规则异常返回
 *
 * 老版本是UrlBlockHandler
 */

@Component
public class ExceptionHandlerPage implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        ResultDto<Object> obj = null;
        if (e instanceof FlowException) {
            obj = new ResultDto<Object>("500", "接口被限流了");
        } else if (e instanceof DegradeException) {
            obj = new ResultDto<Object>("500", "接口被降级了");
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(obj));
    }
}
