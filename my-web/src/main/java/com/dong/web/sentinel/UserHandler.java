package com.dong.web.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.dong.common.dto.ResultDto;
import com.dong.common.util.LogUtil;
import com.dong.common.util.ResponseUtil;
import com.dong.user.dto.UserDto;
import com.dong.web.controller.UserController;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    private static Logger log = LogUtil.get(UserHandler.class);

    /**
     * Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     *
     * @param name
     * @param throwable
     * @return {@link ResultDto<String> }
     */
//    public ResultDto<Object> sayHelloFallback(final String name, final Throwable throwable) {
//        log.error("sayHelloFallback name: {}", name);
//        log.error("资源：{} 发生异常,message is {}", name, throwable.getMessage());
//        return ResponseUtil.error("hello exception");
//    }

    /**
     * BlockHandler 函数
     * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
     *
     * @param name
     * @param e
     * @return {@link ResultDto <String> }
     */
//    public static List<UserDto> sayHelloBlock(final String name, final BlockException e) {
//        log.error("sayHelloBlock name: {}", name);
//        if (e instanceof DegradeException) {
//            log.error("资源：{} 被熔断了,message is {}", name, e.getMessage());
//        } else {
//            log.error("资源：{} 被流控了", name);
//        }
//        return new ArrayList<UserDto>();
//    }

    /**
     * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     * blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析
     *
     * @param exception
     * @return
     */
    public static ResultDto<Object> handleException(BlockException exception) {
        log.info(exception.toString());
        return ResponseUtil.error("users服务熔断降级了...");
    }

}
