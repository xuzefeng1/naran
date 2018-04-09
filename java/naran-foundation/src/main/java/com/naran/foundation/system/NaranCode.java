package com.naran.foundation.system;

/**
 * 平台响应代码
 * @author zefeng.xu
 */
public class NaranCode {

	public static final ErrCode SUCCESS_CODE = new ErrCode("0000", "成功！"); // 平台成功响应代码
	
	public static final ErrCode FAIL_CODE = new ErrCode("9999", "失败！"); // 平台失败响应代码
	
	public static final ErrCode GO_TO_LOGIN_CODE = new ErrCode("8888", "请重新登录！"); // 未登录或登录过期响应代码
	
	public static final ErrCode LOGIN_ACCOUNT_FROZEN_CODE = new ErrCode("7777", "账号被冻结！"); // 账号被冻结响应代码
	
}
