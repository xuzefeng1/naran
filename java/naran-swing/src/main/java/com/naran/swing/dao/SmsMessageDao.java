package com.naran.swing.dao;

import com.naran.swing.entity.message.SmsMessagePO;

/**
 * 短信数据接口
 * @author QuCeng
 */
public interface SmsMessageDao {

	long addSmsMessage(SmsMessagePO message);
	
	void updateSmsMessage(SmsMessagePO message);
	
	SmsMessagePO findSmsMessageByAlidayuSmsId(String alidayuSmsId);
	
}
