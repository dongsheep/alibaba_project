package com.dong.web.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.dong.common.exception.BussinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Sentinel授权规则
 */

//@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    /**
     * 定义分区来源
     *
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        if (StringUtils.isEmpty(serviceName)) {
            throw new BussinessException("serviceName is not empty...");
        }
        return serviceName;
    }
}
