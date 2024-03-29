package com.dong.common.exception;


import com.dong.common.constant.StatusCode;

/**
 * 自定义异常类
 * 
 * @author xiedongxiao
 *
 */

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = -9068786667519810234L;

	private String code;

	private String msg;

	public BussinessException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public BussinessException(StatusCode obj) {
		this.code = obj.getCode();
		this.msg = obj.getMsg();
	}
	
	public BussinessException(String msg) {
		this.code = "500";
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
