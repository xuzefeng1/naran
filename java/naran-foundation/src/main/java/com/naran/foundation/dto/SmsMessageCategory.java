package com.naran.foundation.dto;

/**
 * 短信分类
 * 
 * @author zefeng.xu
 */
public enum SmsMessageCategory {

	REGISTER_VERIFY_CODE("注册验证码"),

	FORGET_PASSWORD_VERIFY_CODE("忘记密码验证码");

	String chinese;

	public String getChinese() {
		return chinese;
	}

	SmsMessageCategory(String chinese) {
		this.chinese = chinese;
	}

}
