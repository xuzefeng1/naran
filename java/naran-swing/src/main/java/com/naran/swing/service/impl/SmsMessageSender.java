package com.naran.swing.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.lechuu.foundation.dto.SmsMessageCategory;
import com.lechuu.foundation.util.StringUtil;
import com.naran.swing.dao.SmsMessageDao;
import com.naran.swing.entity.message.SmsMessagePO;
import com.naran.swing.entity.message.SmsMessagePO.SendState;
import com.naran.swing.entity.message.SmsMessagePO.SmsOperator;
import com.naran.swing.util.SensitiveWordUtil;
import com.naran.swing.util.SmsALDYUtil;

/**
 * 短信发送执行者
 * @author QuCeng
 */
@Component
public class SmsMessageSender {

	protected final Logger logger = LoggerFactory.getLogger("SMS_LOGGER");
	
	private static final String PREFIX_VERIFY_CODE_MINUTE = "minute_";
	private static final String PREFIX_VERIFY_CODE_HOUR = "hour_";
	private static final String PREFIX_VERIFY_CODE_DAY = "day_";
	
	private static final int VALID_SECOND = 10 * 60; // 10分钟
	
	private static final int VERIFY_CODE_MINUTE = 1 * 60; // 1分钟
	private static final int VERIFY_CODE_HOUR = 60 * 60; // 1小时
	private static final int VERIFY_CODE_DAY = 24 * 60 * 60; // 1天
	
	@Autowired
	private SmsMessageDao smsMessageDao;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private SensitiveWordUtil sensitiveWordUtil;
	
	public void send(SmsMessagePO message) {
		if (null == message 
				|| StringUtil.isBlank(message.getMobile()) 
				|| StringUtil.isBlank(message.getContent())) {
			logger.info("短信格式不正确：手机号码或者短信内容为空！");
			return;
		}

		try {
			logger.info("开始处理短信发送！手机号码：" + message.getMobile() + "，短信内容：" + message.getContent());
			
			// 进行号码黑名单过滤（被平台封禁的手机号码，不予短信发送）
			if (sensitiveWordUtil.containsSensitiveMobile(message.getMobile())) {
				logger.info("该手机号码已被平台封禁，不予发送！");
				
				message.setSendState(SendState.COMMIT_ERROR);
				message.setSendStateRemark("该手机号码已被平台封禁，不予发送！");
				return;
			}
			
			// 验证短信发送规则
			// 十分钟内，同一个手机号码+短信类型不能超过3条
			int count = 0;
			String key = message.getMobile() + message.getType();
			String countStr = (String) redisTemplate.opsForValue().get(key);
			if (null != countStr) {
				count = Integer.parseInt(countStr);
				if (3 <= count) {
					message.setSendState(SendState.COMMIT_ERROR);
					message.setSendStateRemark("十分钟内，同一个手机号码+短信类型不能超过3条！");
					return;
				} else {
					// +1
					redisTemplate.opsForValue().increment(key, 1);
				}
			} else {
				// 初始化次数
				redisTemplate.opsForValue().set(key, "1", VALID_SECOND, TimeUnit.SECONDS);
			}
			
			// 验证码类短信
			// 一个分钟内，同一个手机号码+短信类型不能超过1条
			// 一个小时内，同一个手机号码+短信类型不能超过3条
			// 一个整天内，同一个手机号码+短信类型不能超过5条
			int verifyCodeCount = 0;
			String verifyCodeCountStr = null;
			String verifyCodeKeySuffix = "verifyCode" + message.getMobile() + message.getType();
			if (isVerifyCodeSmsType(message.getType())) {
				// 判断分钟
				String verifyCodeKey = PREFIX_VERIFY_CODE_MINUTE + verifyCodeKeySuffix;
				verifyCodeCountStr = (String) redisTemplate.opsForValue().get(verifyCodeKey);
				if (null != verifyCodeCountStr) {
					verifyCodeCount = Integer.parseInt(verifyCodeCountStr);
					if (1 <= verifyCodeCount) {
						message.setSendState(SendState.COMMIT_ERROR);
						message.setSendStateRemark("验证码类短信，一个分钟内，同一个手机号码+短信类型不能超过1条！");
						return;
					} else {
						// +1
						redisTemplate.opsForValue().increment(verifyCodeKey, 1);
					}
				} else {
					// 初始化次数
					redisTemplate.opsForValue().set(verifyCodeKey, "1", VERIFY_CODE_MINUTE, TimeUnit.SECONDS);
				}
				
				// 判断小时
				verifyCodeKey = PREFIX_VERIFY_CODE_HOUR + verifyCodeKeySuffix;
				verifyCodeCountStr = (String) redisTemplate.opsForValue().get(verifyCodeKey);
				if (null != verifyCodeCountStr) {
					verifyCodeCount = Integer.parseInt(verifyCodeCountStr);
					if (3 <= verifyCodeCount) {
						message.setSendState(SendState.COMMIT_ERROR);
						message.setSendStateRemark("验证码类短信，一个小时内，同一个手机号码+短信类型不能超过3条！");
						return;
					} else {
						// +1
						redisTemplate.opsForValue().increment(verifyCodeKey, 1);
					}
				} else {
					// 初始化次数
					redisTemplate.opsForValue().set(verifyCodeKey, "1", VERIFY_CODE_HOUR, TimeUnit.SECONDS);
				}
				
				// 判断天
				verifyCodeKey = PREFIX_VERIFY_CODE_DAY + verifyCodeKeySuffix;
				verifyCodeCountStr = (String) redisTemplate.opsForValue().get(verifyCodeKey);
				if (null != verifyCodeCountStr) {
					verifyCodeCount = Integer.parseInt(verifyCodeCountStr);
					if (5 <= verifyCodeCount) {
						message.setSendState(SendState.COMMIT_ERROR);
						message.setSendStateRemark("验证码类短信，一个整天内，同一个手机号码+短信类型不能超过5条！");
						return;
					} else {
						// +1
						redisTemplate.opsForValue().increment(verifyCodeKey, 1);
					}
				} else {
					// 初始化次数
					redisTemplate.opsForValue().set(verifyCodeKey, "1", VERIFY_CODE_DAY, TimeUnit.SECONDS);
				}
			}
			
			// 过滤掉敏感词
			String contentNew = sensitiveWordUtil.wordFilter(message.getContent());
			message.setContent(contentNew);
			
			// 优先使用阿里大鱼通道
			if (StringUtil.isNotBlank(message.getAlidayuTemplateId())) {
				message.setOperator(SmsOperator.ALIDAYU);
				message = SmsALDYUtil.send(message);
			}
		} catch (Exception e) {
			logger.error("提交通道失败！", e);
			
			message.setSendState(SendState.COMMIT_ERROR);
			message.setSendStateRemark("提交通道失败！");
		} finally {
			// 将该短信插入数据库
			smsMessageDao.addSmsMessage(message);
		}
	}

	// 是否验证码类短信
	private boolean isVerifyCodeSmsType(String smsType) {
		if (StringUtil.isBlank(smsType)) {
			return false;
		}
		
		if (SmsMessageCategory.REGISTER_VERIFY_CODE.name().equalsIgnoreCase(smsType)
			|| SmsMessageCategory.FORGET_PASSWORD_VERIFY_CODE.name().equalsIgnoreCase(smsType)) {
			return true;
		}
		
		return false;
	}
	
}
