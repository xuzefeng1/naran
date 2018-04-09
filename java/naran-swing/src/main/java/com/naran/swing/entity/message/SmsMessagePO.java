package com.naran.swing.entity.message;

import com.lechuu.foundation.entity.BaseEntityPO;

/**
 * 短信
 * @author QuCeng
 */
public class SmsMessagePO extends BaseEntityPO {

	private String mobile; // 手机号码
	
	private String content; // 短信内容
	
	private SendState sendState; // 短信发送状态
	
	private String sendStateRemark; // 短信发送状态备注（通道商返回）
	
	private SmsOperator operator; // 短信运营商
	
	private String type; // 短信类型

	private String template; // 短信模板

	private String alidayuTemplateId; // 阿里大鱼短信模板ID
	
	private String values; // 短信模板参数（{"key1":"value1","key2":"value2"}）
	
	private String alidayuSmsId; // 阿里大鱼短信ID（用于更新最终发送状态）
	
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

	public SendState getSendState() {
		return sendState;
	}

	public void setSendState(SendState sendState) {
		this.sendState = sendState;
	}

	public String getSendStateRemark() {
		return sendStateRemark;
	}

	public void setSendStateRemark(String sendStateRemark) {
		this.sendStateRemark = sendStateRemark;
	}
	
	public SmsOperator getOperator() {
		return operator;
	}

	public void setOperator(SmsOperator operator) {
		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getAlidayuTemplateId() {
		return alidayuTemplateId;
	}

	public void setAlidayuTemplateId(String alidayuTemplateId) {
		this.alidayuTemplateId = alidayuTemplateId;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getAlidayuSmsId() {
		return alidayuSmsId;
	}

	public void setAlidayuSmsId(String alidayuSmsId) {
		this.alidayuSmsId = alidayuSmsId;
	}

	public enum SendState {
		
		COMMIT_SUCCESS("提交成功"), // 提交成功
		
		COMMIT_ERROR("提交失败"), // 提交失败
		
		SEND_SUCCESS("发送成功"), // 发送成功
		
		SEND_ERROR("发送失败"); // 发送失败
		
		private String chinese;

		public String getChinese() {
			return chinese;
		}
		
		private SendState(String chinese) {
			this.chinese = chinese;
		}
	}
	
	public enum SmsOperator {
		
		YIMEI, // 亿美
		
		ALIDAYU, // 阿里大鱼（2015-10-19开始使用）
		
		ZHANGZHONG; // 掌中无线（2015-10-19放弃使用）
	}
}
