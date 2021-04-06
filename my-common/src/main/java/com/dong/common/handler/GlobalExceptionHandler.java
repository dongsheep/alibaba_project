package com.dong.common.handler;

import com.dong.common.dto.ResultDto;
import com.dong.common.exception.BussinessException;
import com.dong.common.util.LogUtil;
import com.dong.common.util.ResponseUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * 
 * @author xiedongxiao
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger log = LogUtil.get(GlobalExceptionHandler.class);

	@ExceptionHandler(value = BussinessException.class)
	public ResultDto<String> bussinessExceptionHandler(BussinessException e) {
		log.error("BussinessException error..." + e.getMsg());
		return ResponseUtil.error(e.getCode(), e.getMsg());
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ResultDto<String> exceptionHandler(Exception e) {
//		log.error("Exception error...", e);
//		return ResponseUtil.error(StatusCode.INTERNAL_ERROR.getCode(), e.getMessage());
//	}

}
