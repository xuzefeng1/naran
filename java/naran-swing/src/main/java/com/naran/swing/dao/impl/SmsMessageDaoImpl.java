package com.naran.swing.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuu.foundation.mybatis.dao.MyBatisDAO;
import com.naran.swing.dao.SmsMessageDao;
import com.naran.swing.entity.message.SmsMessagePO;

/**
 * 短信数据接口实现
 * @author QuCeng
 */
@Repository
public class SmsMessageDaoImpl implements SmsMessageDao {

	private static final String ADD_SMS_MESSAGE = "addSmsMessage";
	private static final String UPDATE_SMS_MESSAGE = "updateSmsMessage";
	private static final String FIND_SMS_MESSAGE_BY_ALIDAYUSMSID = "findSmsMessageByAlidayuSmsId";
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public long addSmsMessage(SmsMessagePO message) {
		message.setCreateTime(new Date());
		
		myBatisDAO.insert(ADD_SMS_MESSAGE, message);
		return message.getId();
	}

	@Override
	public void updateSmsMessage(SmsMessagePO message) {
		message.setUpdateTime(new Date());
		
		myBatisDAO.update(UPDATE_SMS_MESSAGE, message);
	}

	@Override
	public SmsMessagePO findSmsMessageByAlidayuSmsId(String alidayuSmsId) {
		return (SmsMessagePO) myBatisDAO.findForObject(FIND_SMS_MESSAGE_BY_ALIDAYUSMSID, alidayuSmsId);
	}

}
