package com.naran.swing.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.naran.swing.entity.message.SmsMessagePO;
import com.naran.swing.service.impl.SmsMessageSender;

/**
 * activeMQ短信监听器
 * @author QuCeng
 */
public class SmsListener implements MessageListener {

	protected final Logger logger = LoggerFactory.getLogger("SMS_LOGGER");
	
	private SmsMessageSender smsMessageSender;

	public void setSmsMessageSender(SmsMessageSender smsMessageSender) {
		this.smsMessageSender = smsMessageSender;
	}

	/**
	 * 接收处理消息
	 */
	public void onMessage(Message message) {
		logger.info("短信监听器监听到短信队列，开始处理短信！");
		
		TextMessage textMessage = (TextMessage) message;
		try {
			String jsonStr = textMessage.getText();
			logger.info("短信详细内容为："+ jsonStr);
			if (null != jsonStr) {
				SmsMessagePO smsMessage = JSON.parseObject(jsonStr, SmsMessagePO.class);
				smsMessageSender.send(smsMessage);
			}
		} catch (JMSException e) {
			logger.error("处理短信内容失败！", e);
		}
		
		logger.info("短信监听器监听到短信队列，结束处理短信！");
	}
	
}