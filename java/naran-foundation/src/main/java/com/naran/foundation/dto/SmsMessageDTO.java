package com.naran.foundation.dto;

import java.io.Serializable;

/**
 * 短信传值对象
 * @author QuCeng
 */
public class SmsMessageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mobile; // 手机号码
	
	private SmsMessageCategory category; // 短信分类
	
	private String content; // 短信内容（通知类，没有参数的）
	
	private String values; // 短信模板参数（{"key1":"value1","key2":"value2"}）

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SmsMessageCategory getCategory() {
		return category;
	}

	public void setCategory(SmsMessageCategory category) {
		this.category = category;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
	
}
